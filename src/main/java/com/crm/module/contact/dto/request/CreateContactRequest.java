package com.crm.module.contact.dto.request;

import com.crm.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class CreateContactRequest {

    UUID companyId;

    @Size(max = 255, message = "First name limit characters is 225")
    String firstName;

    @Size(max = 255, message = "Last name limit characters is 225")
    @NotBlank(message = "Last name is required")
    String lastName;

    @Size(max = 255, message = "Email limit characters is 225")
    @Email(message = "Invalid email format")
    String email;

    @Size(max = 255, message = "Phone limit characters is 225")
    String phone;

    Address address;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;

    UUID ownerId;
}
