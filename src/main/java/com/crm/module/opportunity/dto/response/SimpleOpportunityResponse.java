package com.crm.module.opportunity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimpleOpportunityResponse {
    private UUID id;
    private String title;
}
