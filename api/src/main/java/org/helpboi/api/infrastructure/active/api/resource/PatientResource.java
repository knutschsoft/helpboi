package org.helpboi.api.infrastructure.active.api.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.patient.CreatePatient;
import org.helpboi.api.application.command.patient.DeletePatient;
import org.helpboi.api.application.command.patient.GetPatient;
import org.helpboi.api.domain.model.patient.Patient;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Maybe;

@Controller("/patients")
public class PatientResource {

	@Inject
	private CommandBus commandBus;
	
	@Get("/{id}")
	@PermitAll
	public Maybe<Patient> getPatient(Long id) {
		return commandBus.execute(new GetPatient(id));
	}
	
	@Post
	@PermitAll
	public Maybe<Patient> createPatient(CreatePatient command) {
		return commandBus.execute(command);
	}
	
	@Delete("/{id}")
	@PermitAll
	public Maybe<Void> deletePatient(Long id) {
		return commandBus.execute(new DeletePatient(id));
	}
}
