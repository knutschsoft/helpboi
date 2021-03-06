package org.helpboi.api.application.handler.patient;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.patient.CreatePatient;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.patient.Patient;
import org.helpboi.api.domain.model.patient.PatientGender;
import org.helpboi.api.domain.model.patient.PatientStatus;

@Singleton
public class CreatePatientHandler implements CommandHandler<CreatePatient> {

    @Inject
    private PatientRepository      patientRepository;
    @Inject
    private OrganisationRepository organisationRepository;
    @Inject
    private CurrentUser            currentUser;

    @Override
    @Transactional
    public void handle(CreatePatient command) {
        Long organisationId = command.getOrganisationId();
        String firstname = command.getFirstname();
        String lastname = command.getLastname();
        PatientGender gender = command.getGender();
        String phone = command.getPhone();
        ZonedDateTime dateOfBirth = command.getDateOfBirth();
        String zipcode = command.getZipcode();
        String city = command.getCity();
        String address = command.getAddress();
        String notes = command.getNotes();
        PatientStatus status = command.getStatus();
        String email = command.getEmail();

        if (!Objects.equals(currentUser.getOrganisationId(), organisationId)) {
            throw new AuthorizationException(String.format(
                    "It's not allowed to create a patient for organisation id: %s", organisationId));
        }

        organisationRepository.findById(organisationId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "Organisation id: %s not found", organisationId)));

        Patient patient = new Patient(
                null, organisationId, firstname, lastname, gender, phone,
                dateOfBirth, zipcode, city, address, notes, status, email
        );
        patient = patientRepository.save(patient);
        command.resolve(patient);
    }
}
