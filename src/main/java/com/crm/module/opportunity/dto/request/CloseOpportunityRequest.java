package com.crm.module.opportunity.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CloseOpportunityRequest {

    @NotNull(message = "Close date is required")
    @PastOrPresent(message = "Close date must be in the past or present")
    LocalDate closedDate;

    @NotNull(message = "is closed successfully is required")
    boolean isClosedSuccessfully ;
}
