package ru.digitalleague.exam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.digitalleague.exam.persistence.Application;
import ru.digitalleague.exam.persistence.User;

import java.util.List;


@Component
@RequiredArgsConstructor
public class OverdueProcessor {
    private final ApplicationService applicationService;

    //@Scheduled(cron = "0 0/20 10-20 * * *")
    @Scheduled(fixedDelay = 30000)
    public void checkTimeoutReservations() {
        applicationService.checkTimeoutReservations();
    }

    @Scheduled(fixedDelay = 30000)
    public void checkLinkConfirming() {
        applicationService.checkUnconfirmedLinks();
    }
}
