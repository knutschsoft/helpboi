package org.helpboi.api.infrastructure.active.api.resource;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.user.CreateUser;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Maybe;

@Controller("/user")
public class UserResource {

	@Inject
	private CommandBus commandBus;

	@Post
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public Maybe<String> createUser(User user) {
		return commandBus.execute(new CreateUser(user));
	}
}
