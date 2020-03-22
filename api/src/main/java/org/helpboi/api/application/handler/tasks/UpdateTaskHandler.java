package org.helpboi.api.application.handler.tasks;

import java.time.ZonedDateTime;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.tasks.UpdateTask;
import org.helpboi.api.application.persistence.PatientRepository;
import org.helpboi.api.application.persistence.TaskRepository;
import org.helpboi.api.application.persistence.UserRepository;
import org.helpboi.api.domain.exception.BusinessException;
import org.helpboi.api.domain.model.task.Task;
import org.helpboi.api.domain.model.task.TaskStatus;

@Singleton
public class UpdateTaskHandler implements CommandHandler<UpdateTask> {

    @Inject
    private TaskRepository      taskRepository;
   
    @Inject
    private PatientRepository   patientRepository;
    
    @Inject
    private UserRepository      userRepository;
   
    @Override
    @Transactional
    public void handle(UpdateTask command) {
    	Long taskId = command.getTaskId();
        Long agentId = command.getAgentId();
        Long patientId = command.getPatientId();
        String content = command.getContent();
        ZonedDateTime activeTo = command.getActiveTo();
        TaskStatus status = command.getStatus();
        
        Task task = taskRepository.findById(taskId)
        		.orElseThrow(() -> new BusinessException(String.format(
        				"Task id: %s not found", taskId)));
        
        patientRepository.findById(patientId)
                .orElseThrow(() -> new BusinessException(String.format(
                        "Patient id: %s not found", patientId)));
        
        if (agentId != null) {
        	userRepository.findById(agentId)
            		.orElseThrow(() -> new BusinessException(String.format(
            				"User id: %s not found", agentId)));
        }

        task.apply(status, activeTo, agentId, content);
        task = taskRepository.update(task);
        command.resolve(task);
    }
}
