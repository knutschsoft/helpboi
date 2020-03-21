package org.helpboi.api.application.handler.patient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.patient.DeletePatient;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.patient.Patient;

@Singleton
public class DeletePatientHandler implements CommandHandler<DeletePatient> {

    @Inject
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public void handle(DeletePatient command) {
        Long patientId = command.getId();
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Patient id: %s not found", patientId)));
        patientRepository.delete(patient);
        command.resolve(null);
    }
}
