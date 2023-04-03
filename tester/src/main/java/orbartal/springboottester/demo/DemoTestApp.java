package orbartal.springboottester.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springboottester.demo.test.CrudMultiErrorTest;
import orbartal.springboottester.demo.test.CrudMultiValidTest;
import orbartal.springboottester.demo.test.CrudOneValidTest;
import orbartal.springboottester.task.api.model.TaskCreateResponseDto;
import orbartal.springboottester.task.app.TaskAppWriter;
import orbartal.springboottester.task.model.RunnableTask;
import orbartal.springboottester.testtask.runnable.TestRunnableTask;
import orbartal.springboottester.testtask.worker.TestTaskWorker;
import orbartal.springboottester.testtask.worker.TestTaskWorkerFactory;

@Component
public class DemoTestApp {

	@Autowired
	private TaskAppWriter taskWriter;

	public TaskCreateResponseDto testCrudOneValid() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudOneValidTest.class);
		RunnableTask task = new TestRunnableTask("testCrudOneValid", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCrudManyValid() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudMultiValidTest.class);
		RunnableTask task = new TestRunnableTask("testCrudManyValid", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCrudManyError() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudMultiErrorTest.class);
		RunnableTask task = new TestRunnableTask("testCrudManyError", worker);
		return taskWriter.runTask(task);
	}

}
