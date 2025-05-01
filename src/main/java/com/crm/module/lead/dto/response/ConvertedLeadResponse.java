package com.crm.module.lead.dto.response;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class ConvertedLeadResponse {
    boolean isConverted;
    UUID contactId;
}
