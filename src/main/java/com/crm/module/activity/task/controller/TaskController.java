package com.crm.module.activity.task.controller;

import com.crm.api.Response;
import com.crm.module.activity.task.dto.request.CreateTaskRequest;
import com.crm.module.activity.task.dto.request.UpdateTaskRequest;
import com.crm.module.activity.task.dto.response.SimpleTaskResponse;
import com.crm.module.activity.task.dto.response.TaskResponse;
import com.crm.module.activity.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PutMapping("/{taskId}")
    public ResponseEntity<Response> updateTask(@Validated @RequestBody UpdateTaskRequest request, @PathVariable UUID taskId){
        SimpleTaskResponse response = taskService.updateTask(request,taskId);
        return ResponseEntity.ok(Response.success("Updated successfully",response, HttpStatus.CREATED));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Response> getTaskById(@PathVariable UUID taskId){
        TaskResponse response = taskService.getTaskById(taskId);
        return ResponseEntity.ok(Response.success("Get successfully",response, HttpStatus.OK));
    }

    @PostMapping("close/{taskId}")
    public ResponseEntity<Response> closeTask(@PathVariable UUID taskId){
        SimpleTaskResponse response = taskService.closeTask(taskId);
        return ResponseEntity.ok(Response.success("Closed successfully",response, HttpStatus.OK));
    }

    @GetMapping("")
    public ResponseEntity<Response> getTasks(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 200);
        List<TaskResponse> response = taskService.getAllTasks(pageable);
        return ResponseEntity.ok(Response.success("Get successfully",response, HttpStatus.OK));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Response> deleteTask(@PathVariable UUID taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok(Response.success("Updated successfully",null, HttpStatus.CREATED));
    }
}
