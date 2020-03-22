package org.helpboi.api.application.command.organisation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.user.User;

public class AddUserToOrganisation  extends Command<User> {

	@Min(1)
    @NotNull
    private Long orgId;
	
	@Min(1)
    @NotNull
    private Long userId;

    public AddUserToOrganisation(Long orgId, Long userId) {
        this.orgId = orgId;
        this.userId = userId;
    }

	public Long getOrgId() {
		return orgId;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AddUserToOrganisation))
			return false;
		AddUserToOrganisation other = (AddUserToOrganisation) obj;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddUserToOrganisation [orgId=" + orgId + ", userId=" + userId + "]";
	}

    
}
