package org.helpboi.api.infrastructure.active.api.exception.handler;

import org.helpboi.api.domain.exception.AuthorizationException;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;

@Controller
public class AuthorizationHandler {

    @Error(exception = AuthorizationException.class, global = true)
    public HttpResponse<JsonError> handle(HttpRequest request, AuthorizationException exception) {
        JsonError body = new JsonError(exception.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse
                .<JsonError>status(HttpStatus.UNAUTHORIZED)
                .body(body);
    }
}
