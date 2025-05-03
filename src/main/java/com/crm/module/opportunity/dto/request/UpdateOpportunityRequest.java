package com.crm.module.opportunity.dto.request;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class UpdateOpportunityRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title characters is 225")
    String title;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;

    @NotNull(message = "Value cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than zero")
    double value;

    @FutureOrPresent(message = "Due date must be in the future or present")
    LocalDate dueDate;

    UUID contactId;

    @NotBlank(message = "Company is required")
    UUID companyId;

    UUID ownerId;
}
