package org.helpboi.api.application.persistence;

import org.helpboi.api.domain.model.hello.Hello;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface HelloRepository extends CrudRepository<Hello, Long> {
}