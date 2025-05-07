package com.crm.module.activity.model;

import com.crm.model.Auditable;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class Activity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RelatedModule relatedModule;

    private UUID relatedId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
