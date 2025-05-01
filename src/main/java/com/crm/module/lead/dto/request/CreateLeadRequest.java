package com.crm.module.lead.dto.request;

import com.crm.model.Address;
import jakarta.persistence.Embedded;

public class CreateLeadRequest {

    @Embedded
    private Address address;
}
