package com.crm.module.opportunity.dto.response;

import com.crm.module.opportunity.model.OpportunityStage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OpportunityResponse {
    private UUID id;
    private String title;
    private String number;
    private String description;
    private double value;
    private LocalDate dueDate;
    private LocalDate closedDate;
    private OpportunityStage stage;
    private UUID contactId;
    private UUID companyId;
    private UUID ownerId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private UUID createdBy;
    private UUID updatedBy;
}