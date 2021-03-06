package org.helpboi.api.infrastructure.active.api.resource;

import java.util.Set;

import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.patient.AddHistoryToPatient;
import org.helpboi.api.application.command.patient.AddSymptomsToPatient;
import org.helpboi.api.application.command.patient.CreatePatient;
import org.helpboi.api.application.command.patient.DeletePatient;
import org.helpboi.api.application.command.patient.GetPatient;
import org.helpboi.api.domain.model.patient.HistoryType;
import org.helpboi.api.domain.model.patient.Patient;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.reactivex.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Patient")
@Controller("/patients")
public class PatientResource {

	@Inject
	private CommandBus commandBus;
	
	@Get("/{id}")
	public Maybe<Patient> getPatient(Long id) {
		return commandBus.execute(
		        new GetPatient(id));
	}
	
	@Post
	public Maybe<Patient> createPatient(@Body CreatePatient command) {
		return commandBus.execute(command);
	}
	
	@Post("/{id}/history")
	public Maybe<Patient> addHistoryToPatient(@PathVariable Long id, HistoryType type, String content) {
		return commandBus.execute(new AddHistoryToPatient(id, type, content));
	}
	
	@Post("/{id}/symptoms")
	public Maybe<Patient> addSymptomsToPatient(Long id, @Body Set<Long> symptomIds) {
		return commandBus.execute(new AddSymptomsToPatient(id, symptomIds));
	}
	
	@Delete("/{id}")
	public Maybe<Void> deletePatient(Long id) {
		return commandBus.execute(
		        new DeletePatient(id));
	}
}
