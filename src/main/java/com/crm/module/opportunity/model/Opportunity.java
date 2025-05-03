package com.crm.module.opportunity.model;

import com.crm.exception.domain.IllegalTransitionException;
import com.crm.model.Auditable;
import com.crm.module.company.model.Company;
import com.crm.module.contact.model.Contact;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Opportunity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String number;

    private String title;

    private String description;

    private double value;

    private LocalDate dueDate;

    private LocalDate closedDate;

    @Enumerated(EnumType.STRING)
    private OpportunityStage stage;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Opportunity(String number, String title, String description, double value, LocalDate dueDate, Company company, Contact contact, User owner) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.value = value;
        this.dueDate = dueDate;
        this.company = company;
        this.contact = contact;
        this.owner = owner;
        this.stage = OpportunityStage.NEW;
    }

    public void update(String title, String description, double value, LocalDate dueDate, Company company, Contact contact, User owner) {
        this.title = title;
        this.description = description;
        this.value = value;
        this.dueDate = dueDate;
        this.company = company;
        this.contact = contact;
        this.owner = owner;
    }

    public void transitionTo(OpportunityStage stage){
        if(!this.stage.canTransitionTo(stage)){
             throw new IllegalTransitionException("Illegal transition from " + stage + " to " + stage);
        }
        this.stage = stage;
    }

    public void closeOpportunity(LocalDate closedDate, boolean isClosedSuccessful) {
        this.closedDate = closedDate;
        if(isClosedSuccessful){
            this.stage = OpportunityStage.WON;
        }else{
            this.stage = OpportunityStage.LOST;
        }
    }

    public UUID getContactId() {
        return contact != null ? contact.getId() : null;

    }

    public UUID getCompanyId() {
        return company != null ? company.getId() : null;
    }

    public UUID getOwnerId() {
        return owner != null ? owner.getId() : null;
    }
}
