package org.helpboi.api.infrastructure.active.api.resource;

import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.tasks.CreateTask;
import org.helpboi.api.application.command.tasks.GetAllTasks;
import org.helpboi.api.domain.model.task.Task;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Task")
@Controller("/tasks")
public class TaskResource {

	@Inject
	private CommandBus commandBus;
	
	@Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Maybe<Task> createTask(@Body CreateTask command) {
        return commandBus.execute(command);
    }
	
	@Get
	public Maybe<Iterable<Task>> getAllTasks() {	
		return commandBus.execute(
		        new GetAllTasks());
	}
}
