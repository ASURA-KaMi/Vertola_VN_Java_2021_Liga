package com.example.liquibasedemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Customer extends NamedEntity {
    @Column
    private Long rating;
    /*
    * @Column(length = 1024)
    * private String address;
    * */
}
