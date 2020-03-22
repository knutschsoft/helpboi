package org.helpboi.api.application.handler.organistaion;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.AddUserToOrganisation;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.user.User;

@Singleton
public class AddUserToOrganisationHandler implements CommandHandler<AddUserToOrganisation> {

    @Inject
    private OrganisationRepository organisationRepository;
    @Inject
    private UserRepository         userRepository;

    @Override
    @Transactional
    public void handle(AddUserToOrganisation command) {
        Long organisationId = command.getOrganisationId();
        Long userId = command.getUserId();

        organisationRepository.findById(organisationId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "Organisation id: %s not found", organisationId)));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "User id: %s not found", userId)));

        user.assignToOrganisation(organisationId);
        user.verifyAssignedOrganisation();

        command.resolve(user);
    }
}
