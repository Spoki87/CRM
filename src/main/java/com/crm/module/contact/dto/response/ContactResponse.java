package com.crm.module.contact.dto.response;

import com.crm.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ContactResponse {
    private UUID id;
    private UUID companyId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Address address;
    private String description;
    private UUID ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdBy;
    private UUID updatedBy;
}
