package org.helpboi.api.application.persistence;

import java.util.Optional;

import org.helpboi.api.domain.model.organisation.Organisation;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface OrganisationRepository extends CrudRepository<Organisation, Long> {
	
	 Optional<Organisation> findByNameAndZipcode(String name, String zipcode);
}
