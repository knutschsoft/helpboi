package org.helpboi.api.application.service.user;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.security.utils.SecurityService;

@Singleton
public class CurrentUser {

    @Inject
    private SecurityService securityService;
    @Inject
    private UserRepository  userRepository;

    public Boolean isAuthenticated() {
        return securityService.isAuthenticated();
    }

    public Optional<User> getUser() {
        if (securityService.isAuthenticated()) {
            Optional<String> email = securityService.username();
            if (email.isPresent()) {
                return userRepository.findByEmail(email.get());
            }
        }
        return Optional.empty();
    }

    public Boolean hasOrganisation() {
        return getUser()
                .map(User::hasOrganisation)
                .orElse(null);
    }

    public Boolean isAdmin() {
        return getUser()
                .map(User::isAdmin)
                .orElse(null);
    }

    public Boolean isVerified() {
        return getUser()
                .map(User::isVerified)
                .orElse(null);
    }

    public Long getId() {
        return getUser()
                .map(User::getId)
                .orElse(null);
    }

    public String getEmail() {
        return getUser()
                .map(User::getEmail)
                .orElse(null);
    }

    public String getPassword() {
        return getUser()
                .map(User::getPassword)
                .orElse(null);
    }

    public String getFirstname() {
        return getUser()
                .map(User::getFirstname)
                .orElse(null);
    }

    public String getLastname() {
        return getUser()
                .map(User::getLastname)
                .orElse(null);
    }

    public String getPhone() {
        return getUser()
                .map(User::getPhone)
                .orElse(null);
    }

    public Long getOrganisationId() {
        return getUser()
                .map(User::getOrganisationId)
                .orElse(null);
    }
}
