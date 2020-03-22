package org.helpboi.api.domain.model.task;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1L)
    @NotNull
    @Column(name = "patient_id")
    private Long patientId;
    
    @Min(1L)
    @Column(name = "agent_id")
    private Long agentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @NotNull
    @Column(name = "active_to")
    private ZonedDateTime activeTo;

    @NotNull
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Lob
    @NotNull
    @NotBlank
    private String content;

    public Task() {
    }

    public Task(
            Long id,
            Long patientId,
            Long agentId,
            ZonedDateTime activeTo,
            String content
    ) {
        Objects.requireNonNull(patientId);
        Objects.requireNonNull(activeTo);
        Objects.requireNonNull(content);

        this.id = id;
        this.patientId = patientId;
        this.agentId = agentId;
        this.status = TaskStatus.OPEN;
        this.createdAt = ZonedDateTime.now();
        this.activeTo = activeTo;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public ZonedDateTime getActiveTo() {
        return activeTo;
    }
    
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", agentId=" + agentId +
                ", status=" + status +
                ", activeTo=" + activeTo +
                ", createdAt=" + createdAt +
                ", content='" + content + '\'' +
                '}';
    }
}
