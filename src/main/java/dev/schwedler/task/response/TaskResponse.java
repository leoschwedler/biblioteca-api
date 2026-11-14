package dev.schwedler.task.response;

import dev.schwedler.task.enums.TaskStatus;

import java.time.Instant;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Instant createdAt
) {
}
