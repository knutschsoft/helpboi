package org.helpboi.api.application.persistence;

import java.util.List;

import org.helpboi.api.domain.model.symptom.Symptom;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface SymptomRepository  extends CrudRepository<Symptom, Long> {
	
	List<Symptom> listOrderByIdAsc();
}
