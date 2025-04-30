package com.crm.module.company.model;

import com.crm.model.Address;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;


    private String description;

    @Embedded
    private Address billingAddress;

    @Embedded
    private Address shippingAddress;
}
