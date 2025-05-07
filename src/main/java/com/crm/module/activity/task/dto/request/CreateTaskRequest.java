package com.crm.module.activity.task.dto.request;

import com.crm.module.activity.model.RelatedModule;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreateTaskRequest {
    String name;
    String description;
    LocalDate dueDate;
    RelatedModule relatedModule;
    UUID relatedId;
    UUID ownerId;
}
