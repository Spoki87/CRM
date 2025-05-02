package com.crm.module.activity.task.model;

import com.crm.model.Auditable;
import com.crm.user.appuser.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Task extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    private String taskName;

    private String description;

    private TaskStatus status;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private RelatedModule relatedModule;

    private UUID relatedEntityId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
