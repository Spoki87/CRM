package com.crm.module.activity.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;


@AllArgsConstructor
@Getter
public class SimpleTaskResponse {
    UUID id;
    String name;
}
