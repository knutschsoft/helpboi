package org.helpboi.api.application.handler.patient;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.patient.DeletePatient;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.domain.model.patient.Patient;

@Singleton
public class DeletePatientHandler implements CommandHandler<DeletePatient> {

    @Inject
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public void handle(DeletePatient command) {
        Optional<Patient> patient = patientRepository.findById(command.getId());
        if (patient.isPresent()) {
        	command.resolve(null);	
        } else {
        	command.notFound();
        }
    }
}
