package org.helpboi.api.application.command.organisation;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.user.User;

public class AddUserToOrganisation  extends Command<User> {

	@Min(1)
    @NotNull
    private Long organisationId;
	
	@Min(1)
    @NotNull
    private Long userId;

    public AddUserToOrganisation(Long organisationId, Long userId) {
        this.organisationId = organisationId;
        this.userId = userId;
    }

	public Long getOrganisationId() {
		return organisationId;
	}

	public Long getUserId() {
		return userId;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddUserToOrganisation that = (AddUserToOrganisation) o;
        return Objects.equals(organisationId, that.organisationId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisationId, userId);
    }

    @Override
    public String toString() {
        return "AddUserToOrganisation{" +
                "organisationId=" + organisationId +
                ", userId=" + userId +
                '}';
    }
}
