package com.crm.module.lead.model;

import com.crm.exception.domain.IllegalTransitionException;
import com.crm.model.Address;
import com.crm.model.Auditable;
import com.crm.module.contact.model.Contact;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "lead")
@Getter
@NoArgsConstructor
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

    @Column(nullable = false)
    private String company;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private LeadSource source;

    @Embedded
    private Address address;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private boolean isConverted;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Lead(String firstName, String lastName, String email, String phone, String company, String description, Address address,LeadSource source, User owner) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.status = LeadStatus.NEW;
        this.description = description;
        this.address = address;
        this.source = source;
        this.isConverted = false;
        this.contact = null;
        this.owner = owner;
    }

    public UUID getContactId() {
        return contact != null ? contact.getId() : null;
    }

    public void update(String firstName, String lastName, String email, String phone, String company, String description, Address address,LeadSource source, User owner){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.company = company;
        this.description = description;
        this.address = address;
        this.source = source;
        this.owner = owner;
    }

    public void transitionTo(LeadStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalTransitionException("Illegal transition from " + this.status + " to " + newStatus);
        }
        this.status = newStatus;
    }

    public void setAsConverted(Contact contact){
        this.contact = contact;
        this.isConverted = true;
        this.status = LeadStatus.CONVERTED;
    }
}
