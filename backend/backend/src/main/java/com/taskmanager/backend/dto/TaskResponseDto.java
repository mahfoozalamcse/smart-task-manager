package com.taskmanager.backend.dto;

import com.taskmanager.backend.entity.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponseDto {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime createdAt;
}