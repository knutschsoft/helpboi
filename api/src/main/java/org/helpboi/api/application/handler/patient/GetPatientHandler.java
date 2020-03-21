package org.helpboi.api.application.handler.patient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.patient.GetPatient;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.patient.Patient;

@Singleton
public class GetPatientHandler implements CommandHandler<GetPatient> {

    @Inject
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public void handle(GetPatient command) {
        Long patientId = command.getId();
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Patient id: %s not found", patientId)));
        command.resolve(patient);
    }
}
