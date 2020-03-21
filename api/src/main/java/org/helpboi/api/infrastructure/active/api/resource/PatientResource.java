package org.helpboi.api.infrastructure.active.api.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.patient.CreatePatient;
import org.helpboi.api.application.command.patient.DeletePatient;
import org.helpboi.api.application.command.patient.GetPatient;
import org.helpboi.api.domain.model.patient.Patient;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.reactivex.Maybe;

@Controller("/patients")
public class PatientResource {

	@Inject
	private CommandBus commandBus;
	
	@Get("/{id}")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Maybe<Patient> getPatient(Long id) {
		return commandBus.execute(new GetPatient(id));
	}
	
	@Post
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Maybe<Patient> createPatient(
	        @Body CreatePatient command
    ) {
		return commandBus.execute(command);
	}
	
	@Delete("/{id}")
	@PermitAll
	public void deletePatient(Long id) {
		commandBus.execute(
		        new DeletePatient(id)
        ).subscribe();
	}
}
