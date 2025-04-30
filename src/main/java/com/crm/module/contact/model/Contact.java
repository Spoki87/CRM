package com.crm.module.contact.model;

import com.crm.model.Address;
import com.crm.module.company.model.Company;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String firstName;

    private String lastName;
    private String email;
    private String phone;

    @Embedded
    private Address address;

    private String description;
}
