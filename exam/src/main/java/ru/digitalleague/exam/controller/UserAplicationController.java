package ru.digitalleague.exam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.exam.dto.ApplicationDTO;
import ru.digitalleague.exam.dto.ShortApplicationDTO;
import ru.digitalleague.exam.service.ApplicationService;
import ru.digitalleague.exam.service.Response;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/schedule/{date}")
    public List<LocalTime> getSchedule(@PathVariable String date) {
        return applicationService.getSchedule(date);
    }

    @PostMapping("/reservation")
    public ApplicationDTO makeReservation(@RequestParam Long userId, @RequestParam String time) {
        return applicationService.makeReservation(new ShortApplicationDTO(userId, LocalDateTime.parse(time))); // TODO: передавать пар-ры отдельно
    }

    @GetMapping("/reservation")
    public List<ShortApplicationDTO> getActiveReservations(@RequestParam Long userId) {
        return applicationService.getActiveReservations(userId);
    }

    @GetMapping("/reservation/{id}/confirming")
    public Response confirmReservationOnLink(@PathVariable Long id) {
        return applicationService.confirmReservationOnLink(id);
    }
}