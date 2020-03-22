package org.helpboi.api.application.command.tasks;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.task.Task;
import org.helpboi.api.domain.model.task.TaskStatus;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class UpdateTask extends Command<Task> {

	@NotNull
    private Long taskId;
	
	@Min(1L)
    @NotNull
    private Long patientId;
    
    @Min(1L)
    private Long agentId;

    @NotNull
    private ZonedDateTime activeTo;

    @NotBlank
    private String content;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public UpdateTask(
    		Long taskId,
    		Long patientId,
    		Long agentId,
    		ZonedDateTime activeTo,
            String content,
            TaskStatus status
    ) {
    	this.taskId = taskId;
        this.patientId = patientId;
        this.agentId = agentId;
        this.activeTo = activeTo;
        this.content = content;
        this.status = status;
    }
    
    public Long getTaskId() {
    	return taskId;
    }
    
    public Long getPatientId() {
		return patientId;
	}

	public Long getAgentId() {
		return agentId;
	}
	
	public ZonedDateTime getActiveTo() {
		return activeTo;
	}

	public String getContent() {
		return content;
	}
	
	public TaskStatus getStatus() {
		return status;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateTask that = (UpdateTask) o;
        return Objects.equals(patientId, that.patientId) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(activeTo, that.activeTo) &&
                Objects.equals(status, that.status) &&
                Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, agentId, content, activeTo, status, taskId);
    }

    @Override
    public String toString() {
        return "UpdateTask{" +
        		"taskId='" + taskId + '\'' +
                "agentId='" + agentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", content='" + content + '\'' +
                ", activeTo='" + activeTo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
