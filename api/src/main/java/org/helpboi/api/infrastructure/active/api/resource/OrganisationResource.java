package org.helpboi.api.infrastructure.active.api.resource;

import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.organisation.CreateOrganisation;
import org.helpboi.api.application.command.organisation.GetOrganisation;
import org.helpboi.api.domain.model.organisation.Organisation;

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
		return commandBus.execute(
		        new GetOrganisation(id));
	}
}
