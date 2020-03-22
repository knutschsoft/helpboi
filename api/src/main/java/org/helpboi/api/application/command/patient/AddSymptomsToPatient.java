package org.helpboi.api.application.command.patient;

import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.patient.Patient;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class AddSymptomsToPatient extends Command<Patient> {
	
	@Min(1)
    @NotNull
    private Long patientId;
	
    @NotNull
    private Set<Long> symptomIds;

    public AddSymptomsToPatient(
    		Long patientId, 
    		Set<Long> symptomIds
    ) {
    	this.patientId = patientId;
        this.symptomIds = symptomIds;
    }

    public Long getPatientId() {
		return patientId;
	}

	public Set<Long> getSymptomIds() {
		return symptomIds;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddSymptomsToPatient that = (AddSymptomsToPatient) o;
        return Objects.equals(symptomIds, that.symptomIds) &&
        	   Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symptomIds, patientId);
    }

    @Override
    public String toString() {
        return "AddSymptomsToPatient{" +
        		"patientId='" + patientId + '\'' +
                "symptomIds='" + symptomIds + '\'' +
                '}';
    }
}
