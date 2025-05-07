package com.crm.module.activity.task.service;

import com.crm.module.activity.task.dto.request.CreateTaskRequest;
import com.crm.module.activity.task.dto.response.SimpleTaskResponse;
import com.crm.module.activity.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public SimpleTaskResponse createTask(CreateTaskRequest request) {
        return null;
    }
}
