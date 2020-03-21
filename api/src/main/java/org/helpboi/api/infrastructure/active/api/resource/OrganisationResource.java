package org.helpboi.api.infrastructure.active.api.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.organisation.GetOrganisation;
import org.helpboi.api.domain.model.organisation.Organisation;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.reactivex.Maybe;

@Controller("/organisations")
public class OrganisationResource {

	@Inject
	private CommandBus commandBus;
	
	@Get("/{id}")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Maybe<Organisation> getOrganisation(Long id) {
		return commandBus.execute(new GetOrganisation(id));
	}
}
