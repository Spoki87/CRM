package com.crm.module.activity.task.service;

import com.crm.exception.domain.UserNotFoundException;
import com.crm.module.activity.service.ActivityService;
import com.crm.module.activity.task.dto.request.CreateTaskRequest;
import com.crm.module.activity.task.dto.request.UpdateTaskRequest;
import com.crm.module.activity.task.dto.response.SimpleTaskResponse;
import com.crm.module.activity.task.dto.response.TaskResponse;
import com.crm.module.activity.task.mapper.TaskMapper;
import com.crm.module.activity.task.model.Task;
import com.crm.module.activity.task.repository.TaskRepository;
import com.crm.user.appuser.model.User;
import com.crm.user.appuser.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final ActivityService activityService;
    private final TaskMapper taskMapper;

    public SimpleTaskResponse createTask(CreateTaskRequest request) {

        Optional<?> relatedEntity = activityService.getRelatedEntity(request.getRelatedModule(),request.getRelatedId());

        if (relatedEntity.isEmpty()) {
            throw new EntityNotFoundException("Entity not found for module " + request.getRelatedModule());
        }

        User owner = userRepository.findById(request.getOwnerId()).orElseThrow(UserNotFoundException::new);

        Task task = new Task(
            request.getRelatedModule(),
                request.getRelatedId(),
                owner,
                request.getName(),
                request.getDescription(),
                request.getDueDate()
        );

        taskRepository.save(task);

        return taskMapper.fromTaskToSimpleTaskResponse(task);
    }

    public List<TaskResponse> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).stream()
                .map(taskMapper::fromTaskToTaskResponse).toList();
    }

    public TaskResponse getTaskById(UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new);
        return taskMapper.fromTaskToTaskResponse(task);
    }

    @Transactional
    public SimpleTaskResponse updateTask(UpdateTaskRequest request, UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new);
        User owner = userRepository.findById(request.getOwnerId()).orElseThrow(UserNotFoundException::new);

        task.update(
                request.getName(),
                request.getDescription(),
                request.getDueDate(),
                owner
        );

        return taskMapper.fromTaskToSimpleTaskResponse(task);
    }

    @Transactional
    public SimpleTaskResponse closeTask(UUID taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new);
        task.close();

        return taskMapper.fromTaskToSimpleTaskResponse(task);
    }

    @Transactional
    public void deleteTask(UUID taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new);
        taskRepository.delete(task);
    }
}
