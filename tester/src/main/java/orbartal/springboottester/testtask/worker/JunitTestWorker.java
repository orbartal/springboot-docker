package orbartal.springboottester.testtask.worker;

import org.junit.runner.Request;
import org.junit.runner.Runner;

import orbartal.springboottester.test.runner.TestListener;
import orbartal.springboottester.testtask.model.TestTaskReport;

public class JunitTestWorker implements TaskWorker<TestTaskReport> {

	private final JunitRunner runner;

	public JunitTestWorker(Class<?> junitTestClass) {
		Runner runner1 = Request.classes(junitTestClass).getRunner();
		this.runner = new JunitRunner(runner1);
	}

	@Override
	public TestTaskReport work() {
		TestListener listener = new TestListener();
		runner.run(listener);
		return new TestTaskReport(listener.getReport());
	}

}
