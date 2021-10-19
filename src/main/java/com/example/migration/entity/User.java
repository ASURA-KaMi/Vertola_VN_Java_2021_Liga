package com.example.migration.entity;

import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public class User {
    @NonNull
    private UUID id;
    @NonNull
    private String full_name;
    @NonNull
    private Integer age;
    @NonNull
    private String sex;
    @NonNull
    private School school;
    private List<Post> postList;
    private List<User> friendList;
}
