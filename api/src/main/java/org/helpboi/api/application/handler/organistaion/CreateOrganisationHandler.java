package org.helpboi.api.application.handler.organistaion;

import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.CreateOrganisation;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.organisation.Organisation;

@Singleton
public class CreateOrganisationHandler implements CommandHandler<CreateOrganisation> {

    @Inject
    private OrganisationRepository organisationRepository;
    @Inject
    private UserRepository         userRepository;
    @Inject
    private CurrentUser            currentUser;

    @Override
    @Transactional
    public void handle(CreateOrganisation command) {
        String name = command.getName();
        String zipcode = command.getZipcode();
        String city = command.getCity();
        String address = command.getAddress();
        Long userId = command.getUserId();

        if (!Objects.equals(currentUser.getId(), userId)) {
            throw new AuthorizationException(String.format(
                    "It's not allowed to create a organisation for user id: %s", userId));
        }

        Optional<Organisation> organisation = organisationRepository
                .findByNameAndZipcode(name, zipcode);
        if (organisation.isPresent()) {
            throw new BusinessException(String.format(
                    "Organisation with name: '%s' and zipcode: '%s' already exists", name, zipcode));
        }

        if (currentUser.hasOrganisation()) {
            throw new BusinessException(String.format(
                    "User id: %s is already assigned to organisation id: %s",
                    userId, currentUser.getOrganisationId()));
        }

        currentUser.getUser().ifPresent((user) -> {
            Organisation finalOrganisation = new Organisation(
                    null, name, zipcode, city, address);
            finalOrganisation = organisationRepository.save(finalOrganisation);

            user.assignToOrganisation(finalOrganisation.getId());
            user.verifyAssignedOrganisation();
            user.promoteToAdmin();

            command.resolve(finalOrganisation);
        });

    }
}
