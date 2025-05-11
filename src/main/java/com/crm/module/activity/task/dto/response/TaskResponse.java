package com.crm.module.activity.task.dto.response;

import com.crm.module.activity.model.RelatedModule;
import com.crm.module.activity.task.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class TaskResponse {
    private UUID id;
    private RelatedModule relatedModule;
    private UUID relatedRecordId;
    private UUID ownerId;
    private String name;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
}
