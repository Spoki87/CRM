package com.crm.module.activity.task.controller;

import com.crm.api.Response;
import com.crm.module.activity.task.dto.request.CreateTaskRequest;
import com.crm.module.activity.task.dto.response.SimpleTaskResponse;
import com.crm.module.activity.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping ("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping()
    public ResponseEntity<Response> createTask(@Validated @RequestBody CreateTaskRequest request){
        SimpleTaskResponse response = taskService.createTask(request);
        return ResponseEntity.ok(Response.success("Created successfully",response, HttpStatus.CREATED));
    }
}
