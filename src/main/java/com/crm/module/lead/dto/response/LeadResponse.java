package com.crm.module.lead.dto.response;

import com.crm.model.Address;
import com.crm.module.lead.model.LeadSource;
import com.crm.module.lead.model.LeadStatus;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class LeadResponse {
    private UUID id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    private String phone;

    private String company;

    private LeadStatus status;

    private LeadSource source;

    private Address address;

    private String description;

    private boolean isConverted;

    private UUID contactId;
}
