package com.crm.module.lead.dto.response;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class SimpleLeadResponse {
    private UUID id;
    private String fullName;
}
