package org.helpboi.api.application.handler.organistaion;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.GetAllOrganisationUsers;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.organisation.Organisation;
import org.helpboi.api.domain.model.user.User;

@Singleton
public class GetAllOrganisationUsersHandler implements CommandHandler<GetAllOrganisationUsers> {

    @Inject
    private OrganisationRepository organisationRepository;
    @Inject
    private UserRepository         userRepository;
    @Inject
    private CurrentUser            currentUser;

    @Override
    @Transactional
    public void handle(GetAllOrganisationUsers command) throws Exception {
        Long organisationId = command.getOrganisationId();

        if (!Objects.equals(currentUser.getOrganisationId(), organisationId)) {
            throw new AuthorizationException(String.format(
                    "The user is not allowed to see users for organisation id: %s", organisationId));
        }

        Organisation organisation = organisationRepository.findById(organisationId)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Organisation id: %s not found", organisationId)));

        List<User> organisationUsers = userRepository
                .findAllByOrganisationId(organisationId);
        command.resolve(organisationUsers);
    }
}
