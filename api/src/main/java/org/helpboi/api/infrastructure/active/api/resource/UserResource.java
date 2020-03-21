package org.helpboi.api.infrastructure.active.api.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.user.CreateUser;
import org.helpboi.api.application.command.user.DeleteUser;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Post;
import io.reactivex.Maybe;

@Controller("/users")
public class UserResource {

	@Inject
	private CommandBus commandBus;
	
	@Post
	@PermitAll
	public Maybe<User> createUser(CreateUser command) {
		return commandBus.execute(command);
	}
	
	@Delete("/{id}")
	@PermitAll
	public Maybe<Void> deleteUser(Long id) {
		return commandBus.execute(new DeleteUser(id));
	}
}
