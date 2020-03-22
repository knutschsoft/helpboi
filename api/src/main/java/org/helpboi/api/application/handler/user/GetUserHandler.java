package org.helpboi.api.application.handler.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.GetUser;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.user.User;

@Singleton
public class GetUserHandler implements CommandHandler<GetUser> {

    @Inject
    private UserRepository userRepository;
    @Inject
    private CurrentUser    currentUser;

    @Override
    @Transactional
    public void handle(GetUser command) {
        Long userId = command.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "User id: %s not found", userId)));

        Set<Long> allowedUserIds = new HashSet<>();
        allowedUserIds.add(currentUser.getId());

        if (currentUser.hasOrganisation()) {
            List<User> organisationUsers = userRepository
                    .findAllByOrganisationId(currentUser.getOrganisationId());
            for (User organisationUser : organisationUsers) {
                allowedUserIds.add(organisationUser.getId());
            }
        }

        if (!allowedUserIds.contains(userId)) {
            throw new AuthorizationException(String.format(
                    "It's not allowed to get user id: %s", userId));
        }

        command.resolve(user);
    }
}
