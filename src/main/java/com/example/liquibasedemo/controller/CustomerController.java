package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.DTO.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.exceptions.ServiceGetEntityException;
import com.example.liquibasedemo.exceptions.ServiceUpdateEntityException;
import com.example.liquibasedemo.persistence.CustomerRepository;
import com.example.liquibasedemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/crud/customer")
@RequiredArgsConstructor
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @ApiOperation(value = "Enumerates all CustomerDTO entities")
    @GetMapping
    public List<CustomerDTO> enumerate() {
        return customerService.enumerate();
    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @ApiOperation(value = "Retrieves CustomerDTO entity by it ID")
    @GetMapping("/{id}")
    public CustomerDTO get(@PathVariable("id")String id) throws ServiceGetEntityException {
        return customerService.getById(id);
    }


    @ApiOperation(value = "Delete Customer entity by it ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")String id) throws ServiceGetEntityException {
        customerService.deleteById(id);
    }
    @ApiOperation(value = "Update Customer entity by it ID")
    @PutMapping
    public Customer update(@PathVariable("id")String id, @RequestBody Customer customer) throws ServiceUpdateEntityException {
        return customerService.update(id, customer);
    }

}
