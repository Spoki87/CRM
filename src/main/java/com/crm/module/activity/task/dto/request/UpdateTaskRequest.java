package com.crm.module.activity.task.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class UpdateTaskRequest {

    @Size(max = 255, message = "Limit characters is 225")
    @NotBlank(message = "Company name is required")
    String name;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be today or in the future")
    LocalDate dueDate;

    @NotNull(message = "Owner ID is required")
    UUID ownerId;
}
