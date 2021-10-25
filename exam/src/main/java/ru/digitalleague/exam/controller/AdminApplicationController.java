package ru.digitalleague.exam.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.exam.dto.ApplicationDTO;
import ru.digitalleague.exam.service.ApplicationService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor

public class AdminApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/reservation/closest")
    public ApplicationDTO getClosestReservation() {
        return applicationService.getClosestReservation();
    }

    @PutMapping("/reservation/{id}/arrived")
    public ApplicationDTO markReservationAsArrived(@PathVariable Long id) {
        return applicationService.markReservationAsArrived(id);
    }

    @PutMapping("/reservation/{id}/done")
    public ApplicationDTO markReservationAsDone(@PathVariable Long id) {
        return applicationService.markReservationAsDone(id);
    }

    @PutMapping("/reservation/{id}/cancelled")
    public ApplicationDTO cancelReservation(@PathVariable Long id) {
        return applicationService.markReservationAsCancelled(id);
    }
}
