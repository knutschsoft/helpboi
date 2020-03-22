package org.helpboi.api.application.handler.patient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.patient.AddHistoryToPatient;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.patient.History;
import org.helpboi.api.domain.model.patient.HistoryType;
import org.helpboi.api.domain.model.patient.Patient;

@Singleton
public class AddHistoryToPatientHandler implements CommandHandler<AddHistoryToPatient> {

    @Inject
    private PatientRepository patientRepository;
   
    @Inject
    private CurrentUser currentUser;
    
    @Override
    @Transactional
    public void handle(AddHistoryToPatient command) {
        Long patientId = command.getPatientId();
        HistoryType historyType = command.getHistoryType();
        String content = command.getContent();
        
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "Patient id: %s not found", patientId)));
        
        History history = new History(
        		null, patient, currentUser.getId(), historyType, content);
        
        patient.addHistory(history);
        patient = patientRepository.update(patient);
        command.resolve(patient);
    }
}