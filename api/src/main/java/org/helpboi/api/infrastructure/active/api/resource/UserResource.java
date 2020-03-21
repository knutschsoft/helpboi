package org.helpboi.api.infrastructure.active.api.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.user.CreateUser;
import org.helpboi.api.application.command.user.DeleteUser;
import org.helpboi.api.application.command.user.GetUser;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.reactivex.Maybe;

@Controller("/users")
public class UserResource {

	@Inject
	private CommandBus commandBus;
	
	@Get("/{id}")
	@PermitAll
	public Maybe<User> getUser(Long id) {
		return commandBus.execute(new GetUser(id));
	}
	
	@Post
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Maybe<User> createUser(@Body CreateUser command) {
		return commandBus.execute(command);
	}
	
	@Delete("/{id}")
	@PermitAll
	public Maybe<Void> deleteUser(Long id) {
		return commandBus.execute(new DeleteUser(id));
	}
}
