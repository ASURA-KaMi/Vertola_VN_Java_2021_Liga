package com.example.migration.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {
    @NonNull
    private UUID id;
    private String post_data;
}
