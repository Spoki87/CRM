package com.crm.module.company.dto.request;

import com.crm.model.Address;
import com.crm.module.company.model.CompanyType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateCompanyRequest {

    @Size(max = 255, message = "Limit characters is 225")
    @NotBlank(message = "Company name is required")
    String name;

    @Size(max = 255, message = "Limit characters is 225")
    @Email(message = "Invalid email format")
    String email;

    String phone;

    @NotBlank(message = "Tax identification number is required")
    String taxIdentificationNumber;

    CompanyType type;

    String industry;

    String website;

    int numberOfEmployees;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;

    Address billingAddress;

    Address shippingAddress;

    UUID ownerId;
}
