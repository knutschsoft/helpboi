package org.helpboi.api.infrastructure.active.api.resource;

import java.util.List;

import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.organisation.CreateOrganisation;
import org.helpboi.api.application.command.organisation.GetAllOrganisationPatients;
import org.helpboi.api.application.command.organisation.GetAllOrganisationUsers;
import org.helpboi.api.application.command.organisation.GetOrganisation;
import org.helpboi.api.domain.model.organisation.Organisation;
import org.helpboi.api.domain.model.patient.Patient;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Organisation")
@Controller("/organisations")
public class OrganisationResource {

	@Inject
	private CommandBus commandBus;
	
	@Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Maybe<Organisation> createOrganisation(@Body CreateOrganisation command) {
        return commandBus.execute(command);
    }
	
	@Get("/{id}")
	public Maybe<Organisation> getOrganisation(Long id) {
		return commandBus.execute(new GetOrganisation(id));
	}

	@Get("/{id}/users")
	public Maybe<List<User>> getAllOrganisationUsers(Long id) {
	    return commandBus.execute(new GetAllOrganisationUsers(id));
    }
	
	@Get("/{id}/patients")
	public Maybe<List<Patient>> getAllOrganisationPatients(Long id) {
	    return commandBus.execute(new GetAllOrganisationPatients(id));
    }
}
