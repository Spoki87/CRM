package com.crm.module.activity.task.dto.request;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class UpdateTask {
    String name;
    String description;
    LocalDate dueDate;
    UUID ownerId;
}
