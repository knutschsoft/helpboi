package org.helpboi.api.application.command.symptom;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.symptom.Symptom;

public class GetAllSymptoms  extends Command<Iterable<Symptom>> {
    
    @Override
    public String toString() {
        return "GetAllSymptoms{}";
    }
}
