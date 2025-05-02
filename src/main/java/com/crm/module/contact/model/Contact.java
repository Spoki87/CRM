package com.crm.module.contact.model;

import com.crm.model.Address;
import com.crm.model.Auditable;
import com.crm.module.company.model.Company;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Contact extends Auditable {

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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Contact(Company company, String firstName, String lastName, String email, Address address, String phone, String description, User owner) {
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.owner = owner;
    }

    public void update(Company company, String firstName, String lastName, String email, Address address, String phone, String description, User owner){
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.owner = owner;
    }

    public UUID getCompanyId() {
        return company != null ? company.getId() : null;
    }

    public UUID getOwnerId() {
        return owner != null ? owner.getId() : null;
    }
}
