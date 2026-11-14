package com.example.task.service;

import com.example.task.entity.Task;
import com.example.task.mapper.TaskMapper;
import com.example.task.repository.TaskRepository;

import com.example.exception.ResourceNotFoundException;
import com.example.task.request.TaskRequest;
import com.example.task.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Transactional(readOnly = true)
    public List<TaskResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        return mapper.toResponse(getOrThrow(id));
    }

    @Transactional
    public TaskResponse create(TaskRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional
    public TaskResponse update(Long id, TaskRequest request) {
        Task task = getOrThrow(id);
        mapper.update(request, task);
        return mapper.toResponse(task);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    private Task getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task %d não encontrada".formatted(id)));
    }
}
