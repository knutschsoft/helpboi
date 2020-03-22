package org.helpboi.api.application.command.patient;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.patient.HistoryType;
import org.helpboi.api.domain.model.patient.Patient;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AddHistoryToPatient extends Command<Patient> {
	
	@Min(1)
    @NotNull
    private Long patientId;
	
	@NotNull
	private HistoryType historyType;
	
    @NotBlank
    private String content;

    public AddHistoryToPatient(
    		Long patientId, 
    		HistoryType historyType,
    		String content
    ) {
    	this.patientId = patientId;
    	this.historyType = historyType;
        this.content = content;
    }

    public Long getPatientId() {
		return patientId;
	}

	public HistoryType getHistoryType() {
		return historyType;
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
        AddHistoryToPatient that = (AddHistoryToPatient) o;
        return historyType == that.historyType &&
        	   Objects.equals(content, that.content) &&
        	   Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historyType, content, patientId);
    }

    @Override
    public String toString() {
        return "AddHistoryToPatient{" +
        		"historyType='" + historyType + '\'' +
        		"patientId='" + patientId + '\'' +
                "content='" + content + '\'' +
                '}';
    }
}
