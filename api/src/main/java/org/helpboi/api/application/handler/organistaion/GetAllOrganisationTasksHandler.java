package org.helpboi.api.application.handler.organistaion;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.organisation.GetAllOrganisationTasks;
import org.helpboi.api.application.persistence.OrganisationRepository;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.application.persistence.TaskRepository;
import org.helpboi.api.application.service.user.CurrentUser;
import org.helpboi.api.domain.exception.AuthorizationException;
import org.helpboi.api.domain.exception.NotFoundException;
import org.helpboi.api.domain.model.task.Task;

@Singleton
public class GetAllOrganisationTasksHandler implements CommandHandler<GetAllOrganisationTasks> {

    @Inject
    private OrganisationRepository organisationRepository;
    
    @Inject
    private TaskRepository taskRepository;
    
    @Inject
    private PatientRepository patientRepository;
  
    @Inject
    private CurrentUser currentUser;

    @Override
    @Transactional
    public void handle(GetAllOrganisationTasks command) throws Exception {
        Long organisationId = command.getOrganisationId();

        if (!Objects.equals(currentUser.getOrganisationId(), organisationId)) {
            throw new AuthorizationException(String.format(
                "The user is not allowed to see tasks for organisation id: %s", organisationId));
        }

        organisationRepository.findById(organisationId)
            .orElseThrow(() -> new NotFoundException(String.format(
                "Organisation id: %s not found", organisationId)));

        List<Long> patientIds = new LinkedList<>();
        patientRepository.findAllByOrganisationId(organisationId)
        	.forEach(p -> patientIds.add(p.getId()));;
        
        
        List<Task> organisationTasks = taskRepository
            .findByPatientIdInList(patientIds);
        
        command.resolve(organisationTasks);
    }
}
