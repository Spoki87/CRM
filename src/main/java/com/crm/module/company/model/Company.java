package com.crm.module.company.model;

import com.crm.model.Address;
import com.crm.model.Auditable;
import com.crm.module.contact.model.Contact;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Company extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String name;

    private String email;

    private String phone;

    @Column(unique = true)
    private String taxIdentificationNumber;

    @Enumerated(EnumType.STRING)
    private CompanyType type;

    private String industry;

    private String website;

    private Integer numberOfEmployees;

    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "billing_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country"))
    })
    private Address billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "shipping_street")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "shipping_country"))
    })
    private Address shippingAddress;

    @OneToMany(mappedBy = "company")
    List<Contact> contacts;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Company(String name, String email, String phone, String taxIdentificationNumber, CompanyType type, String industry, String website, int numberOfEmployees, String description, Address billingAddress, Address shippingAddress, User owner) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.type = type;
        this.industry = industry;
        this.website = website;
        this.numberOfEmployees = numberOfEmployees;
        this.description = description;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.owner = owner;
    }

    public Company(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public void update(String name, String email, String phone, String taxIdentificationNumber, CompanyType type, String industry, String website, int numberOfEmployees, String description, Address billingAddress, Address shippingAddress, User owner){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.type = type;
        this.industry = industry;
        this.website = website;
        this.numberOfEmployees = numberOfEmployees;
        this.description = description;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.owner = owner;
    }

}
