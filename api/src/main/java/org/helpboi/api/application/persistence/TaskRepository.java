package org.helpboi.api.application.persistence;

import java.util.List;

import org.helpboi.api.domain.model.task.Task;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> listOrderByActiveToDesc();
}
