package com.crm.module.company.dto.response;

import com.crm.model.Address;
import com.crm.module.company.model.CompanyType;
import com.crm.module.contact.dto.response.SimpleContactResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Getter
public class CompanyResponse {
    private UUID id;

    private String name;

    private String email;

    private String phone;

    private String taxIdentificationNumber;

    private CompanyType type;

    private String industry;

    private String website;

    private Integer numberOfEmployees;

    private String description;

    private Address billingAddress;

    private Address shippingAddress;

    private List<SimpleContactResponse> contacts;

    private UUID ownerId;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private UUID createdBy;

    private UUID modifiedBy;
}
