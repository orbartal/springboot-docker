package orbartal.springboottester.task.app;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springboottester.task.api.model.TaskCreateResponseDto;
import orbartal.springboottester.task.app.mapper.TaskResponseFactory;
import orbartal.springboottester.task.data.TaskDataWriter;
import orbartal.springboottester.task.model.RunnableTask;


@Component
public class TaskAppWriter {

	@Autowired
	private TaskResponseFactory responseFactory;

	@Autowired
	private TaskDataWriter taskExecuter;

	public TaskCreateResponseDto runTask(RunnableTask task) {
		UUID uuid = taskExecuter.addNewTask(task);
		return responseFactory.create(uuid, task);
	}

}
