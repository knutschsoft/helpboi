package org.helpboi.api.application.handler.patient;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.patient.AddSymptomsToPatient;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.patient.Patient;

@Singleton
public class AddSymptomsToPatientHandler implements CommandHandler<AddSymptomsToPatient> {

    @Inject
    private PatientRepository   patientRepository;
   
    @Override
    @Transactional
    public void handle(AddSymptomsToPatient command) {
        Long patientId = command.getPatientId();
        Set<Long> symptomIds = command.getSymptomIds();
        
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "Patient id: %s not found", patientId)));
        
        patient.applySymptomIds(symptomIds);
        patient = patientRepository.update(patient);
        command.resolve(patient);
    }
}