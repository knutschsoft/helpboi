package org.helpboi.api.application.handler.patient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.patient.CreatePatient;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.domain.model.patient.Patient;

@Singleton
public class CreatePatientHandler implements CommandHandler<CreatePatient> {

    @Inject
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public void handle(CreatePatient command) {
        Patient patient = new Patient(null,
        		command.getFirstname(),
        		command.getLastname(),
        		command.getGender(),
        		command.getPhone(),
        		command.getDateOfBirth(),
        		command.getZipcode(),
        		command.getCity(),
        		command.getAddress(),
        		command.getNotes()
                );
        patient = patientRepository.save(patient);
        command.resolve(patient);
    }
}
