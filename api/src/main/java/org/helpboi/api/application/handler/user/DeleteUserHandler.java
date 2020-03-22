package org.helpboi.api.application.handler.user;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.DeleteUser;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.user.User;

import io.micronaut.security.utils.SecurityService;

@Singleton
public class DeleteUserHandler implements CommandHandler<DeleteUser> {

    @Inject
    private UserRepository  userRepository;
    @Inject
    private SecurityService securityService;
    @Inject
    private CurrentUser     currentUser;

    @Override
    @Transactional
    public void handle(DeleteUser command) {
        Long userId = command.getId();

        if (!Objects.equals(currentUser.getId(), userId)) {
            throw new AuthorizationException(String.format(
                    "it's not allowed to delete user id: %s", userId));
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "User id: %s not found", userId)));

        userRepository.delete(user);
        command.resolve(null);
    }
}
