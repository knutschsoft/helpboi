package org.helpboi.api.application.handler.organistaion;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.GetOrganisation;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.organisation.Organisation;

@Singleton
public class GetOrganisationHandler implements CommandHandler<GetOrganisation> {

    @Inject
    private OrganisationRepository organisationRepository;

    @Override
    @Transactional
    public void handle(GetOrganisation command) {
        Long organisationId = command.getId();
        Organisation organisation = organisationRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Organisation id: %s not found", organisationId)));
        command.resolve(organisation);
    }
}
