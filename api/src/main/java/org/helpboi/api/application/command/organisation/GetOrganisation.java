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
        Objects.requireNonNull(id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GetOrganisation))
			return false;
		GetOrganisation other = (GetOrganisation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GetOrganisation [id=" + id + "]";
	}

    
}
