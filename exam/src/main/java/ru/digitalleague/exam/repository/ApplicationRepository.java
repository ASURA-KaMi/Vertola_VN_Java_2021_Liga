package ru.digitalleague.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.digitalleague.exam.persistence.Application;
import ru.digitalleague.exam.persistence.Status;
import ru.digitalleague.exam.persistence.User;
;import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByUserAndStatus(User user, Status status);

    Application findByTime(LocalDateTime time);

    List<Application> findAllByTimeBetween(LocalDateTime time1, LocalDateTime time2);

    Application findFirstByStatusAndTimeAfter(Status status, LocalDateTime time);

    List<Application> findAllByStatusAndTimeBefore(Status status, LocalDateTime time);

    List<Application> findAllByStatusAndCreationTimeBefore(Status status, LocalDateTime time);
}
