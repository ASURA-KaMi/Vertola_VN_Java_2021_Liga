package com.example.liquibasedemo.service;

import com.example.liquibasedemo.DTO.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.exceptions.ServiceGetEntityException;
import com.example.liquibasedemo.exceptions.ServiceUpdateEntityException;
import com.example.liquibasedemo.mappers.CustomerMapper;
import com.example.liquibasedemo.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    public List<CustomerDTO> enumerate(){
        return customerRepository.findAll().stream().map(customerMapper::mapToCustomerDto).collect(Collectors.toList());

    }
    public CustomerDTO getById(String id) throws ServiceGetEntityException {
        if (!customerRepository.existsById(UUID.fromString(id))){
            throw new ServiceGetEntityException(String.format("No such user by id: %s", id));
        }
        else{
            return customerMapper.mapToCustomerDto(customerRepository.findById(UUID.fromString(id)).get());
        }
    }

    public Customer save(Customer customer){
        return  customerRepository.save(customer);
    }

    public Customer update(String id, Customer customer) throws ServiceUpdateEntityException {
        Customer updatedCustomer;
        if (!customerRepository.existsById(UUID.fromString(id))){
            throw new ServiceUpdateEntityException(String.format("No such user by id: %s", id));
        }
        else{
            updatedCustomer = customerRepository.getById(UUID.fromString(id));
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setId(customer.getId());
            updatedCustomer.setRating(customer.getRating());
            return save(updatedCustomer);
        }
    }

    public void deleteById(String id) throws ServiceGetEntityException {
        if (!customerRepository.existsById(UUID.fromString(id))){
            throw new ServiceGetEntityException(String.format("No such user by id: %s", id));
        }
        else{
            customerRepository.deleteById(UUID.fromString(id));
        }
    }
}
