package org.helpboi.api.application.command.tasks;

import org.helpboi.api.application.Command;
import org.helpboi.api.domain.model.task.Task;

public class GetAllTasks extends Command<Iterable<Task>> {

    public GetAllTasks() {}

	@Override
	public String toString() {
		return "GetAllTasks []";
	}
}
