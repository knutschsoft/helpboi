package org.helpboi.api.domain.model.organisation;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "organisation_user")
public class OrganisationUser {

    @EmbeddedId
    private OrganisationUserId id;

    @ManyToOne
    @JoinColumn(name = "organisation_id", insertable = false, updatable = false)
    private Organisation organisation;

    @NotNull
    private Boolean isAdmin;
    @NotNull
    private Boolean isVerified;

    public OrganisationUser() {
    }

    public OrganisationUser(
            Organisation organisation,
            Long userId,
            Boolean isAdmin,
            Boolean isVerified
    ) {
        Objects.requireNonNull(organisation);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(isAdmin);
        Objects.requireNonNull(isVerified);

        this.id = new OrganisationUserId(
                organisation.getId(), userId);
        this.organisation = organisation;
        this.isAdmin = isAdmin;
        this.isVerified = isVerified;
    }

    public OrganisationUserId getId() {
        return id;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public Long getUserId() {
        return id.getUserId();
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrganisationUser that = (OrganisationUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrganisationUser{" +
                "id=" + id +
                ", organisation=" + organisation +
                ", isAdmin=" + isAdmin +
                ", isVerified=" + isVerified +
                '}';
    }
}
