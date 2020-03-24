package org.helpboi.api.application.command.organisation;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.task.Task;

public class GetAllOrganisationTasks extends Command<List<Task>> {

    @Min(1L)
    @NotNull
    private Long organisationId;

    public GetAllOrganisationTasks(Long organisationId) {
        this.organisationId = organisationId;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetAllOrganisationTasks that = (GetAllOrganisationTasks) o;
        return Objects.equals(organisationId, that.organisationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisationId);
    }

    @Override
    public String toString() {
        return "GetAllOrganisationTasks{" +
                "organisationId=" + organisationId +
                '}';
    }
}
