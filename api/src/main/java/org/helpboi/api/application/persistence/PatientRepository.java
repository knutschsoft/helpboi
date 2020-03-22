package org.helpboi.api.application.persistence;

import java.util.List;

import org.helpboi.api.domain.model.patient.Patient;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PatientRepository  extends CrudRepository<Patient, Long> {
	
	List<Patient> findAllByOrganisationId(Long organisationId);
}
