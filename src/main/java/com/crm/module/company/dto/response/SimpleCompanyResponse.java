package com.crm.module.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimpleCompanyResponse {
    private UUID id;
    private String name;
}
