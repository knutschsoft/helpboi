package org.helpboi.api.infrastructure.active.api.resource;

import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.user.CreateUser;
import org.helpboi.api.application.command.user.DeleteUser;
import org.helpboi.api.application.command.user.GetUser;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User")
@Controller("/users")
public class UserResource {

    @Inject
    private CommandBus commandBus;

    @Get("/{id}")
    public Maybe<User> getUser(Long id) {
        return commandBus.execute(
                new GetUser(id));
    }

    @Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Maybe<User> createUser(@Body CreateUser command) {
        return commandBus.execute(command);
    }

    @Delete("/{id}")
    public Maybe<Void> deleteUser(Long id) {
        return commandBus.execute(
                new DeleteUser(id));
    }
}
