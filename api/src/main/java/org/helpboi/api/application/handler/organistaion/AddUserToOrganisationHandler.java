package org.helpboi.api.application.handler.organistaion;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.AddUserToOrganisation;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.organisation.Organisation;
import org.helpboi.api.domain.model.user.User;

@Singleton
public class AddUserToOrganisationHandler implements CommandHandler<AddUserToOrganisation> {

	@Inject
    private OrganisationRepository  organisationRepository;
    
    @Inject
    private UserRepository  userRepository;

    @Override
    @Transactional
    public void handle(AddUserToOrganisation command) {
        Long organisationId = command.getOrgId();
        Long userId = command.getUserId();
        
        Optional<Organisation> org = organisationRepository.findById(organisationId);
        if (!org.isPresent()) {
            throw new BusinessException(
                    String.format("An organisation with the id: '%s' does not exists.", organisationId));
        }
        
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new BusinessException(
                    String.format("An user with the id: '%s' does not exist.", userId));
        }
        
        User finalUser = user.get();
        finalUser.assignToOrganisation(organisationId, false);
        command.resolve(finalUser);
    }
}
