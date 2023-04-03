package orbartal.springboottester.example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springboottester.example.test.CallOrderTest;
import orbartal.springboottester.example.test.Test4Results;
import orbartal.springboottester.task.api.model.TaskCreateResponseDto;
import orbartal.springboottester.task.app.TaskAppWriter;
import orbartal.springboottester.task.model.RunnableTask;
import orbartal.springboottester.testtask.runnable.TestRunnableTask;
import orbartal.springboottester.testtask.worker.TestTaskWorker;
import orbartal.springboottester.testtask.worker.TestTaskWorkerFactory;

@Component
public class ExampleTestApp {

	@Autowired
	private TaskAppWriter taskWriter;

	public TaskCreateResponseDto test4Results() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClass(Test4Results.class);
		RunnableTask task = new TestRunnableTask("test4Results", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCallOrder() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClass(CallOrderTest.class);
		RunnableTask task = new TestRunnableTask("testCallOrder", worker);
		return taskWriter.runTask(task);
	}

}
