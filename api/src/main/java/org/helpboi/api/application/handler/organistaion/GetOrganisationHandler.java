package org.helpboi.api.application.handler.organistaion;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.GetOrganisation;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.domain.model.organisation.Organisation;

@Singleton
public class GetOrganisationHandler implements CommandHandler<GetOrganisation> {

    @Inject
    private OrganisationRepository orgRepository;

    @Override
    @Transactional
    public void handle(GetOrganisation command) {
        Optional<Organisation> org = orgRepository.findById(command.getId());
        if (org.isPresent()) {
        	command.resolve(org.get());
        } else {
        	command.notFound();
        }
    }
}
