package org.helpboi.api.application.command.tasks;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.task.Task;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CreateTask extends Command<Task> {

	@Min(1L)
    @NotNull
    private Long patientId;
    
    @Min(1L)
    private Long agentId;

    @NotNull
    private ZonedDateTime activeTo;
    
    @NotNull
    @NotBlank
    private String content;

    public CreateTask(
    		Long patientId,
    		Long agentId,
    		ZonedDateTime activeTo,
            String content
    ) {
        this.patientId = patientId;
        this.agentId = agentId;
        this.activeTo = activeTo;
        this.content = content;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateTask that = (CreateTask) o;
        return Objects.equals(patientId, that.patientId) &&
                Objects.equals(agentId, that.agentId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(activeTo, that.activeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, agentId, content, activeTo);
    }

    @Override
    public String toString() {
        return "CreateTask{" +
                "agentId='" + agentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", content='" + content + '\'' +
                ", activeTo='" + activeTo + '\'' +
                '}';
    }
}
