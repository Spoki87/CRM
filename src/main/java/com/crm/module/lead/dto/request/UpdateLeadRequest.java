package com.crm.module.lead.dto.request;

import com.crm.model.Address;
import com.crm.module.lead.model.LeadSource;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateLeadRequest {

    @Size(max = 255, message = "First name limit characters is 225")
    String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name limit characters is 225")
    String lastName;

    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email limit characters is 225")
    String email;

    @Size(max = 255, message = "Phone limit characters is 225")
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
