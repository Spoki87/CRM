package com.crm.module.contact.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SimpleContactResponse {
    private UUID id;
    private String fullName;
}
