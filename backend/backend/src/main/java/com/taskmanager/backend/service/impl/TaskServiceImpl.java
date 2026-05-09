package com.taskmanager.backend.service.impl;

import com.taskmanager.backend.dto.TaskRequestDto;
import com.taskmanager.backend.exception.UnauthorizedException;
import com.taskmanager.backend.dto.TaskResponseDto;
import com.taskmanager.backend.entity.Task;
import com.taskmanager.backend.entity.TaskStatus;
import com.taskmanager.backend.entity.User;
import com.taskmanager.backend.exception.ResourceNotFoundException;
import com.taskmanager.backend.repository.TaskRepository;
import com.taskmanager.backend.service.TaskService;
import com.taskmanager.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    @Override
    public TaskResponseDto createTask(
            TaskRequestDto request
    ) {

        User currentUser =
                userService.getCurrentUser();

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .user(currentUser)
                .build();

        Task savedTask =
                taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {

        User currentUser =
                userService.getCurrentUser();

        return taskRepository.findByUser(currentUser)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public TaskResponseDto updateTask(
            Long id,
            TaskRequestDto request
    ) {

        User currentUser =
                userService.getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"
                        )
                );

        if (!task.getUser().getId()
                .equals(currentUser.getId())) {

            throw new UnauthorizedException(
                    "Unauthorized access"
            );
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());

        Task updatedTask =
                taskRepository.save(task);

        return mapToResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {

        User currentUser =
                userService.getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"
                        )
                );

        if (!task.getUser().getId()
                .equals(currentUser.getId())) {

            throw new UnauthorizedException(
                    "Unauthorized access"
            );
        }

        taskRepository.delete(task);
    }

    @Override
    public List<TaskResponseDto> searchTasks(
            String keyword
    ) {

        User currentUser =
                userService.getCurrentUser();

        return taskRepository
                .findByUserAndTitleContainingIgnoreCase(
                        currentUser,
                        keyword
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TaskResponseDto> filterByStatus(
            TaskStatus status
    ) {

        User currentUser =
                userService.getCurrentUser();

        return taskRepository
                .findByUserAndStatus(
                        currentUser,
                        status
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TaskResponseDto mapToResponse(
            Task task
    ) {

        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .build();
    }
}