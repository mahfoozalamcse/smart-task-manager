package com.taskmanager.backend.service;

import com.taskmanager.backend.dto.TaskRequestDto;
import com.taskmanager.backend.dto.TaskResponseDto;
import com.taskmanager.backend.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto request);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto updateTask(
            Long id,
            TaskRequestDto request
    );

    void deleteTask(Long id);

    List<TaskResponseDto> searchTasks(String keyword);

    List<TaskResponseDto> filterByStatus(TaskStatus status);
}