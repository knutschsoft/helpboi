package org.helpboi.api.application.handler.organistaion;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.CreateOrganisation;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.organisation.Organisation;
import org.helpboi.api.domain.model.user.User;

@Singleton
public class CreateOrganisationHandler implements CommandHandler<CreateOrganisation> {

    @Inject
    private OrganisationRepository  organisationRepository;
    
    @Inject
    private UserRepository  userRepository;

    @Override
    @Transactional
    public void handle(CreateOrganisation command) {
        String name = command.getName();
        String zipcode = command.getZipcode();
        String city = command.getCity();
        String address = command.getAddress();
        Long userId = command.getUserId();

        Optional<Organisation> org = organisationRepository.findByNameAndZipcode(name, zipcode);
        if (org.isPresent()) {
            throw new BusinessException(
                    String.format("An organisation with this name: '%s' and zipcode: '%s' already exists.", name, zipcode));
        }
        
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new BusinessException(
                    String.format("An user with the id: '%s' does not exist.", userId));
        }

        Organisation finalOrganisation = new Organisation(
                null, name, zipcode, city, address);
        finalOrganisation = organisationRepository.save(finalOrganisation);
        user.get().assignToOrganisation(finalOrganisation.getId(), true);

        command.resolve(finalOrganisation);
    }
}
