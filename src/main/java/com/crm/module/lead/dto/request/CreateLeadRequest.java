package com.crm.module.lead.dto.request;

import com.crm.model.Address;
import com.crm.module.lead.model.LeadSource;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class CreateLeadRequest {

    @Size(max = 255, message = "Limit characters is 225")
    String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Limit characters is 225")
    String lastName;

    @Size(max = 255, message = "Limit characters is 225")
    @Email(message = "Invalid email format")
    String email;

    String phone;

    @Size(max = 255, message = "Limit characters is 225")
    @NotBlank(message = "Company is required")
    String company;

    LeadSource source;

    Address address;

    UUID ownerId;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;
}
