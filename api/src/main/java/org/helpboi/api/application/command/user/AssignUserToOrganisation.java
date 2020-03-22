package org.helpboi.api.application.command.user;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.user.User;

public class AssignUserToOrganisation extends Command<User> {

    @Min(1)
    @NotNull
    private Long userId;
    @Min(1)
    @NotNull
    private Long organisationId;

    public AssignUserToOrganisation(Long userId, Long organisationId) {
        this.userId = userId;
        this.organisationId = organisationId;
    }

    public Long getUserId() {
        return userId;
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
        AssignUserToOrganisation that = (AssignUserToOrganisation) o;
        return Objects.equals(organisationId, that.organisationId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisationId, userId);
    }

    @Override
    public String toString() {
        return "AssignUserToOrganisation{" +
                "organisationId=" + organisationId +
                ", userId=" + userId +
                '}';
    }
}
