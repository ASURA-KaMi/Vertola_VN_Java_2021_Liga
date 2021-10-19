package com.example.migration.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class School {
    @NonNull
    private UUID id;
    @NonNull
    private String title;
    @NonNull
    private String address;
}
