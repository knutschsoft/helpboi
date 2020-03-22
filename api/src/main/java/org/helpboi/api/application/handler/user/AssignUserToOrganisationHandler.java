package org.helpboi.api.application.handler.user;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.user.AssignUserToOrganisation;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.BusinessException;

@Singleton
public class AssignUserToOrganisationHandler implements CommandHandler<AssignUserToOrganisation> {

    @Inject
    private OrganisationRepository organisationRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private CurrentUser    currentUser;

    @Override
    @Transactional
    public void handle(AssignUserToOrganisation command) {
        Long userId = command.getUserId();
        Long organisationId = command.getOrganisationId();

        if (!Objects.equals(currentUser.getId(), userId)) {
            throw new AuthorizationException(String.format(
                    "it's not allowed to assign user id: %s to organisation id: %s", userId, organisationId));
        }

        organisationRepository.findById(organisationId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "Organisation id: %s not found", organisationId)));

        if (currentUser.hasOrganisation()) {
            throw new BusinessException(String.format(
                    "User id: %s is already assigned to organisation id: %s",
                    userId, currentUser.getOrganisationId()));
        }

        currentUser.getUser().ifPresent((user) -> {
            user.assignToOrganisation(organisationId);
            user.verifyAssignedOrganisation();

            command.resolve(user);
        });
    }
}
