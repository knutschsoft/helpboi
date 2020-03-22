package org.helpboi.api.application.persistence;

import org.helpboi.api.domain.model.task.Task;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
