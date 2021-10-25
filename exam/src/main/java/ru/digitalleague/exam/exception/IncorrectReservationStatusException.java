package ru.digitalleague.exam.exception;

import lombok.Getter;
import lombok.Setter;
import ru.digitalleague.exam.persistence.Status;

@Getter
@Setter
public class IncorrectReservationStatusException extends RuntimeException {
    private Status necessaryStatus;
    private Status currentStatus;

    public IncorrectReservationStatusException(Status necessaryStatus, Status currentStatus) {
        super(String.format("Reservation status must be %s, current status is %s", necessaryStatus, currentStatus));
        this.necessaryStatus = necessaryStatus;
        this.currentStatus = currentStatus;
    }
}
