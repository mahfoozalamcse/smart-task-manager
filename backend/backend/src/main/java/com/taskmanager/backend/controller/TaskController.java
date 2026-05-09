package com.taskmanager.backend.controller;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import com.taskmanager.backend.dto.TaskRequestDto;
import com.taskmanager.backend.dto.TaskResponseDto;
import com.taskmanager.backend.entity.TaskStatus;
import com.taskmanager.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponseDto createTask(
            @RequestBody TaskRequestDto request
    ) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequestDto request
    ) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(
            @PathVariable Long id
    ) {

        taskService.deleteTask(id);

        return "Task deleted successfully";
    }

    @GetMapping("/search")
    public List<TaskResponseDto> searchTasks(
            @RequestParam String keyword
    ) {
        return taskService.searchTasks(keyword);
    }

    @GetMapping("/status")
    public List<TaskResponseDto> filterByStatus(
            @RequestParam TaskStatus status
    ) {
        return taskService.filterByStatus(status);
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TaskResponseDto> getAllTasksForAdmin() {

        return taskService.getAllTasks();
    }
}