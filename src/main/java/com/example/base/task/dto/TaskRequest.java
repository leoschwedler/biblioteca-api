package com.example.base.task.dto;

import com.example.base.task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank @Size(max = 120) String title,
        @Size(max = 500) String description,
        TaskStatus status
) {
}
