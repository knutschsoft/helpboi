package org.helpboi.api.application.persistence;

import java.util.List;
import java.util.Optional;

import org.helpboi.api.domain.model.user.User;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findAllByOrganisationId(Long organisationId);
}
