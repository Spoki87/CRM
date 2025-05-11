package com.crm.module.activity.model;

import com.crm.model.Auditable;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class Activity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RelatedModule relatedModule;

    private UUID relatedRecordId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Activity(RelatedModule relatedModule, UUID relatedRecordId,User owner) {
        this.relatedModule = relatedModule;
        this.relatedRecordId = relatedRecordId;
        this.owner = owner;
    }

}
