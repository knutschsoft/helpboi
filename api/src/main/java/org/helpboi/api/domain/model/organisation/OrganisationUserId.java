package org.helpboi.api.domain.model.organisation;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrganisationUserId implements Serializable {

    @Column(name = "organisation_id")
    private Long organisationId;
    @Column(name = "user_id")
    private Long userId;

    public OrganisationUserId() {
    }

    public OrganisationUserId(
            Long organisationId,
            Long userId
    ) {
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
        OrganisationUserId that = (OrganisationUserId) o;
        return Objects.equals(organisationId, that.organisationId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisationId, userId);
    }

    @Override
    public String toString() {
        return "OrganisationUserId{" +
                "organisationId=" + organisationId +
                ", userId=" + userId +
                '}';
    }
}
