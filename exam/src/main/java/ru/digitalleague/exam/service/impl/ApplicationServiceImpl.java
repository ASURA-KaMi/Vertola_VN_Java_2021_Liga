package ru.digitalleague.exam.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.digitalleague.exam.dto.ApplicationDTO;
import ru.digitalleague.exam.dto.ShortApplicationDTO;
import ru.digitalleague.exam.exception.EntityNotFoundException;
import ru.digitalleague.exam.exception.IncorrectReservationStatusException;
import ru.digitalleague.exam.exception.ReservationNotAvailableException;
import ru.digitalleague.exam.persistence.Application;
import ru.digitalleague.exam.persistence.Status;
import ru.digitalleague.exam.persistence.User;
import ru.digitalleague.exam.repository.ApplicationRepository;
import ru.digitalleague.exam.repository.UserRepository;
import ru.digitalleague.exam.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.exam.service.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    private LocalTime openingHours = LocalTime.MIN;

    private LocalTime closingHours= LocalTime.MAX;

    @Value("${application.interval}")
    private Integer intervalMinutes;

    @Value("${application.ttl}")
    private Integer linkExpirationTimeMinutes;

    @Value("${service.hostname}")
    private String appHost;

    @Value("${server.port}")
    private String appPort;

    @Override
    public List<LocalTime> getSchedule(String date) {
        LocalDate scheduledDate = LocalDate.parse(date);
        List<LocalTime> schedule = new ArrayList<>();

        List<Application> reservations = applicationRepository.findAllByTimeBetween
                (LocalDateTime.of(scheduledDate, openingHours), LocalDateTime.of(scheduledDate, closingHours));

        List<LocalDateTime> reservedTimeSlots = reservations.stream()
                .map(Application::getTime)
                .collect(Collectors.toList());

        LocalDateTime iterationTime = LocalDateTime.of(scheduledDate, openingHours);
        LocalDateTime closingHoursOnScheduledDate = LocalDateTime.of(scheduledDate, closingHours);
        LocalDateTime now = LocalDateTime.now();
        while (iterationTime.isBefore(closingHoursOnScheduledDate)) {
            if (iterationTime.isAfter(now) && !reservedTimeSlots.contains(iterationTime)) {
                schedule.add(iterationTime.toLocalTime());
            }
            iterationTime = iterationTime.plusMinutes(intervalMinutes);
        }
        return schedule;
    }

    @Override
    @Transactional
    public ApplicationDTO makeReservation(ShortApplicationDTO reservationDTO) {
        User user = userRepository.findById(reservationDTO.getUser())
                .orElseThrow(() -> new EntityNotFoundException(User.class.getName(), reservationDTO.getUser()));

        LocalDateTime reservationTime = reservationDTO.getTime();
        validateReservationTime(reservationTime);
        Application savedReservation = applicationRepository.save(new Application(null, user,
                reservationTime, LocalDateTime.now(), Status.UNCONFIRMED));

        StringBuilder messageWithLink = new StringBuilder("Your confirmation link: http://")
                .append(appHost).append(":").append(appPort).append("/user/reservation/")
                .append(savedReservation.getId()).append("/confirming").append("\nIt will be available only for ")
                .append(linkExpirationTimeMinutes).append(" minutes!");
        System.out.println(messageWithLink);

        return new ApplicationDTO(savedReservation.getId(), savedReservation.getUser().getId(),
                savedReservation.getTime(), savedReservation.getStatus());
    }

    @Override
    public List<ShortApplicationDTO> getActiveReservations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class.getName(), userId));

        return applicationRepository.findAllByUserAndStatus(user, Status.ACTIVE).stream()
                .map(this::convertReservationToShortDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO getClosestReservation() {
        return Optional.ofNullable(applicationRepository.findFirstByStatusAndTimeAfter(Status.ACTIVE,
                        LocalDateTime.now()))
                .map(this::convertReservationToDTO)
                .orElseThrow(() -> new RuntimeException("No new reservations"));
    }

    @Override
    @Transactional
    public ApplicationDTO markReservationAsArrived(Long reservationId) {
        Application reservation = applicationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException(Application.class.getName(), reservationId));

        if (!reservation.getStatus().equals(Status.ACTIVE)) {
            throw new IncorrectReservationStatusException(Status.ACTIVE, reservation.getStatus());
        }

        reservation.setStatus(Status.ARRIVED);
        return convertReservationToDTO(applicationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ApplicationDTO markReservationAsDone(Long reservationId) {
        Application reservation = applicationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException(Application.class.getName(), reservationId));

        if (!reservation.getStatus().equals(Status.ARRIVED)) {
            throw new IncorrectReservationStatusException(Status.ARRIVED, reservation.getStatus());
        }

        reservation.setStatus(Status.COMPLETED);
        return convertReservationToDTO(applicationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ApplicationDTO markReservationAsCancelled(Long reservationId) {
        Application reservation = applicationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException(Application.class.getName(), reservationId));

        if (!reservation.getStatus().equals(Status.ACTIVE)) {
            throw new IncorrectReservationStatusException(Status.ACTIVE, reservation.getStatus());
        }

        reservation.setStatus(Status.CANCELED);
        return convertReservationToDTO(applicationRepository.save(reservation));
    }

    @Override
    @Transactional
    public void checkTimeoutReservations() {
        List<Application> timeoutReservations = applicationRepository.findAllByStatusAndTimeBefore(Status.ACTIVE,
                LocalDateTime.now().minusMinutes(intervalMinutes));

        timeoutReservations.forEach(r -> {
            r.setStatus(Status.TIMEOUT);
            applicationRepository.save(r);
        });
    }

    @Override
    @Transactional
    public void checkUnconfirmedLinks() {
        List<Application> unconfirmedReservations =
                applicationRepository.findAllByStatusAndCreationTimeBefore(Status.UNCONFIRMED,
                        LocalDateTime.now().minusMinutes(linkExpirationTimeMinutes));

        unconfirmedReservations.forEach(r -> r.setStatus(Status.ANNULLED));
    }

    @Override
    @Transactional
    public Response confirmReservationOnLink(Long reservationId) {
        Application reservation = applicationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException(Application.class.getName(), reservationId));

        Status status = reservation.getStatus();
        switch (status) {
            case UNCONFIRMED:
                reservation.setStatus(Status.ACTIVE);
                applicationRepository.save(reservation);
                return new Response("You successfully confirmed your reservation");
            case ANNULLED:
                return new Response("Your confirmation link expired");
            case ACTIVE:
                return new Response("Your reservation has already been confirmed");
            default:
                throw new RuntimeException("Bad status of reservation");
        }
    }

    private void validateReservationTime(LocalDateTime time) {
        Application existingReservation = applicationRepository.findByTime(time);
        if (time.isBefore(LocalDateTime.now())
                || (existingReservation != null && (existingReservation.getStatus().equals(Status.ACTIVE)
                || existingReservation.getStatus().equals(Status.UNCONFIRMED)))
                || time.getMinute() % intervalMinutes != 0
                || time.toLocalTime().isBefore(openingHours)
                || time.toLocalTime().isAfter(closingHours.minusMinutes(1))) {
            throw new ReservationNotAvailableException(time);
        }
    }

    private ShortApplicationDTO convertReservationToShortDTO(Application reservation) {
        return new ShortApplicationDTO(reservation.getUser().getId(), reservation.getTime());
    }

    private ApplicationDTO convertReservationToDTO(Application reservation) {
        return new ApplicationDTO(reservation.getId(), reservation.getUser().getId(),
                reservation.getTime(), reservation.getStatus());
    }
}
