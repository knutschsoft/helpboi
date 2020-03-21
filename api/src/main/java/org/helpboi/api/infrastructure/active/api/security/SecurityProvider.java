package org.helpboi.api.infrastructure.active.api.security;

import java.util.Collections;
import java.util.Objects;

import javax.inject.Singleton;

import org.helpboi.api.infrastructure.active.api.misc.BCrypt;
import org.reactivestreams.Publisher;

import io.micronaut.context.annotation.Property;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.reactivex.Flowable;

@Singleton
public class SecurityProvider implements AuthenticationProvider, PasswordEncoder {

    @Property(name = "micronaut.security.basic.username")
    private String username;
    @Property(name = "micronaut.security.basic.password")
    private String password;

    @Override
    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(
            AuthenticationRequest authenticationRequest
    ) {
        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        if (Objects.equals(this.username, username)
                && Objects.equals(this.password, password)) {
            return Flowable.just(
                    new UserDetails(
                            username,
                            Collections.emptyList()
                    )
            );
        }
        return Flowable.just(
                new AuthenticationFailed());
    }
}