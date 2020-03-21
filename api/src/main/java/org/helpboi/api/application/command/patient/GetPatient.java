package org.helpboi.api.application.command.patient;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.patient.Patient;

public class GetPatient extends Command<Patient> {

	@Min(1)
    @NotNull
    private Long id;

    public GetPatient(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetPatient that = (GetPatient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GetPatient{" +
                "id=" + id +
                '}';
    }
}
