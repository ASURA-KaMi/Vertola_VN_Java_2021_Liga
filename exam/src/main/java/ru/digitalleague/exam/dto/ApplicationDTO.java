package ru.digitalleague.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.digitalleague.exam.persistence.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApplicationDTO {
    private Long id;
    private Long user;
    private LocalDateTime time;
    private Status status;
}
