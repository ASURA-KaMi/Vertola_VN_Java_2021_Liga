package com.example.liquibasedemo.mappers;

import com.example.liquibasedemo.DTO.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDTO mapToCustomerDto(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName());
    }
}
