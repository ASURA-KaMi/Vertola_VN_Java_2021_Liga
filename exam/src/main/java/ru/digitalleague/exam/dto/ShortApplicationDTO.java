package ru.digitalleague.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Getter
    @Setter
    @AllArgsConstructor
    public class ShortApplicationDTO {
        private Long user;
        private LocalDateTime time;
    }

