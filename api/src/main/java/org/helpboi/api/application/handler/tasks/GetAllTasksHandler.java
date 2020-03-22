package org.helpboi.api.application.handler.tasks;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import org.helpboi.api.application.CommandHandler;
import org.helpboi.api.application.command.tasks.GetAllTasks;
import org.helpboi.api.application.persistence.TaskRepository;
import org.helpboi.api.domain.model.task.Task;

@Singleton
public class GetAllTasksHandler implements CommandHandler<GetAllTasks> {

    @Inject
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public void handle(GetAllTasks command) {
    	Iterable<Task> symptoms = taskRepository.listOrderByActiveToDesc();
        command.resolve(symptoms);
    }
}
