package org.helpboi.api.application.handler.tasks;

import java.time.ZonedDateTime;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.tasks.CreateTask;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.application.persistence.TaskRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.task.Task;

@Singleton
public class CreateTaskHandler implements CommandHandler<CreateTask> {

    @Inject
    private TaskRepository      taskRepository;
   
    @Inject
    private PatientRepository      patientRepository;
   
    @Override
    @Transactional
    public void handle(CreateTask command) {
        Long agentId = command.getAgentId();
        Long patientId = command.getPatientId();
        String content = command.getContent();
        ZonedDateTime activeTo = command.getActiveTo();
        
        patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "Patient id: %s not found", patientId)));

        Task task = new Task(
                null, agentId, patientId, activeTo, content
        );
        
        task = taskRepository.save(task);
        command.resolve(task);
    }
}
