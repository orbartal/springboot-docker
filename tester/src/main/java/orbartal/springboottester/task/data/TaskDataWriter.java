package orbartal.springboottester.task.data;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springboottester.task.dao.TaskDb;
import orbartal.springboottester.task.model.RunnableTask;


@Component
public class TaskDataWriter {

	//Should use virtual thread in Java 19
	private final ExecutorService executor = Executors.newFixedThreadPool(5);

	@Autowired
	private TaskDb taskDb;

	public UUID addNewTask(RunnableTask task) {
		UUID uid = UUID.randomUUID();
		taskDb.addTask(uid, task);
		executor.submit(task);
		return uid;
	}

}
