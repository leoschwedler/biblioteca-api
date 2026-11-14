package dev.schwedler.task.entity;

import dev.schwedler.task.enums.TaskStatus;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.Instant;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    // Preenchido pelo DEFAULT now() da migration; o Hibernate só lê de volta.
    @Column(insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private Instant createdAt;
}
