package com.example.liquibasedemo.DTO;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@ApiModel(description = "Customer DTO")
public class CustomerDTO {
    private UUID id;
    private String name;
}
