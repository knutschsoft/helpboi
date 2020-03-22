package org.helpboi.api.application.handler.symptom;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.symptom.GetAllSymptoms;
import org.helpboi.api.application.persistence.SymptomRepository;
import org.helpboi.api.domain.model.symptom.Symptom;

@Singleton
public class GetAllSymptomsHandler implements CommandHandler<GetAllSymptoms> {

    @Inject
    private SymptomRepository symptomRepository;

    @Override
    @Transactional
    public void handle(GetAllSymptoms command) {
    	Iterable<Symptom> symptoms = symptomRepository.findAll();
        command.resolve(symptoms);
    }
}
