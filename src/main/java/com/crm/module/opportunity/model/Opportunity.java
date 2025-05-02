package com.crm.module.opportunity.model;

import com.crm.model.Auditable;
import com.crm.module.company.model.Company;
import com.crm.module.contact.model.Contact;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    private BigDecimal value;

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

}
