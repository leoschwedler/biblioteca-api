package com.example.base.task;

import com.example.base.task.dto.TaskRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class TaskMapperTest {

    private final TaskMapper mapper = Mappers.getMapper(TaskMapper.class);

    @Test
    void toEntityCopiesFields() {
        Task task = mapper.toEntity(new TaskRequest("Estudar", "MapStruct", TaskStatus.DOING));

        assertThat(task.getId()).isNull();
        assertThat(task.getTitle()).isEqualTo("Estudar");
        assertThat(task.getStatus()).isEqualTo(TaskStatus.DOING);
    }

    @Test
    void updateIgnoresNulls() {
        Task task = Task.builder().title("A").description("keep").status(TaskStatus.TODO).build();

        mapper.update(new TaskRequest("B", null, null), task);

        assertThat(task.getTitle()).isEqualTo("B");
        assertThat(task.getDescription()).isEqualTo("keep");
        assertThat(task.getStatus()).isEqualTo(TaskStatus.TODO);
    }
}
