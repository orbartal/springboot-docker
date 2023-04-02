package orbartal.springboottester.example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springboottester.example.test.Test1;
import orbartal.springboottester.task.api.model.TaskCreateResponseDto;
import orbartal.springboottester.task.app.TaskAppWriter;
import orbartal.springboottester.task.model.RunnableTask;
import orbartal.springboottester.testtask.runnable.TestRunnableTask;
import orbartal.springboottester.testtask.worker.JunitTestWorker;

@Component
public class ExampleTestApp {

	@Autowired
	private TaskAppWriter taskWriter;

	public TaskCreateResponseDto test1() {
		JunitTestWorker worker = new JunitTestWorker(Test1.class);
		RunnableTask task = new TestRunnableTask("test1", worker);
		return taskWriter.runTask(task);
	}

}
