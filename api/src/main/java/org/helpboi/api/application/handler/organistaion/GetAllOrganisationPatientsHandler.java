package org.helpboi.api.application.handler.organistaion;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.GetAllOrganisationPatients;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.patient.Patient;

@Singleton
public class GetAllOrganisationPatientsHandler implements CommandHandler<GetAllOrganisationPatients> {

    @Inject
    private OrganisationRepository organisationRepository;
    
    @Inject
    private PatientRepository patientRepository;
  
    @Inject
    private CurrentUser currentUser;

    @Override
    @Transactional
    public void handle(GetAllOrganisationPatients command) throws Exception {
        Long organisationId = command.getOrganisationId();

        if (!Objects.equals(currentUser.getOrganisationId(), organisationId)) {
            throw new AuthorizationException(String.format(
                    "The user is not allowed to see patients for organisation id: %s", organisationId));
        }

        organisationRepository.findById(organisationId)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Organisation id: %s not found", organisationId)));

        List<Patient> organisationPatients = patientRepository
                .findAllByOrganisationId(organisationId);
        command.resolve(organisationPatients);
    }
}
