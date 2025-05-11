package com.crm.module.activity.task.mapper;

import com.crm.module.activity.task.dto.response.SimpleTaskResponse;
import com.crm.module.activity.task.dto.response.TaskResponse;
import com.crm.module.activity.task.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public SimpleTaskResponse fromTaskToSimpleTaskResponse(Task task) {

        return new SimpleTaskResponse(
                task.getId(),
                task.getTaskName()
        );
    }

    public TaskResponse fromTaskToTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getRelatedModule(),
                task.getRelatedRecordId(),
                task.getOwner().getId(),
                task.getTaskName(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate()
        );
    }

}
