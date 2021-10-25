package ru.digitalleague.exam.service;

import ru.digitalleague.exam.dto.ApplicationDTO;
import ru.digitalleague.exam.dto.ShortApplicationDTO;
import ru.digitalleague.exam.persistence.Application;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<LocalTime> getSchedule(String date);

    ApplicationDTO makeReservation(ShortApplicationDTO reservationDTO);

    List<ShortApplicationDTO> getActiveReservations(Long userId);

    ApplicationDTO getClosestReservation();

    ApplicationDTO markReservationAsArrived(Long reservationId);

    ApplicationDTO markReservationAsDone(Long reservationId);

    ApplicationDTO markReservationAsCancelled(Long reservationId);

    void checkTimeoutReservations();

    void checkUnconfirmedLinks();

    Response confirmReservationOnLink(Long reservationId);
}
