package org.helpboi.api.application.command.organisation;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.organisation.Organisation;

public class GetOrganisation  extends Command<Organisation> {

	@Min(1)
    @NotNull
    private Long id;

    public GetOrganisation(Long id) {
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
        GetOrganisation that = (GetOrganisation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "GetOrganisation{" +
                "id=" + id +
                '}';
    }
}
