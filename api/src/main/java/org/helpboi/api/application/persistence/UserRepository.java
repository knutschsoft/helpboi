package org.helpboi.api.application.persistence;

import org.helpboi.api.domain.model.user.User;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
