package org.helpboi.api.infrastructure.active.api.resource;

import javax.inject.Inject;

import org.helpboi.api.application.CommandBus;
import org.helpboi.api.application.command.auth.LoginUser;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth")
@Controller("/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AuthResource {

    @Inject
    private CommandBus commandBus;

    @Post
    public Maybe<User> loginUser(@Body LoginUser command) {
        return commandBus.execute(command);
    }
}
