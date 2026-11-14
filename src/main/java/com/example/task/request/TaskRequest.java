package com.example.task.request;

import com.example.task.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank @Size(max = 120) String title,
        @Size(max = 500) String description,
        @NotNull TaskStatus status
) {
}
