package org.helpboi.api.infrastructure.active.api.resource;

import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.symptom.GetAllSymptoms;
import org.helpboi.api.domain.model.symptom.Symptom;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Symptom")
@Controller("/symptoms")
public class SymptomResource {

	@Inject
	private CommandBus commandBus;
	
	@Get
	public Maybe<Iterable<Symptom>> getAllSymptoms() {	
		return commandBus.execute(
		        new GetAllSymptoms());
	}
}
