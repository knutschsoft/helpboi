package org.helpboi.api.infrastructure.active.api.security;

import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.model.user.User;
import org.helpboi.api.infrastructure.active.api.misc.BCrypt;
import org.reactivestreams.Publisher;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.reactivex.Flowable;

@Singleton
public class SecurityProvider implements AuthenticationProvider, PasswordEncoder {

    @Inject
    private UserRepository userRepository;

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
        String email = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        User user = userRepository
                .findByEmail(email)
                .orElse(null);

        if (user == null) {
            return Flowable.just(
                    new AuthenticationFailed());
        }

        if (!matches(password, user.getPassword())) {
            return Flowable.just(
                    new AuthenticationFailed());
        }

        return Flowable.just(
                new UserDetails(
                        user.getEmail(),
                        Collections.emptyList()
                )
        );
    }
}