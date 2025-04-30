package com.crm.module.lead.model;

import com.crm.model.Address;
import com.crm.model.Auditable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "lead")
public class Lead extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String company;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private String description;

    @Embedded
    private Address address;
}
