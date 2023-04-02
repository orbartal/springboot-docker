package orbartal.springboottester.testtask.worker;

import org.junit.runner.JUnitCore;

import orbartal.springboottester.test.runner.TestBeanRunner;
import orbartal.springboottester.test.runner.TestListener;
import orbartal.springboottester.testtask.model.TestTaskReport;

public class TestRunnerWorker implements TaskWorker<TestTaskReport> {

	private final TestBeanRunner test;

	public TestRunnerWorker(TestBeanRunner test) {
		this.test = test;
	}

	@Override
	public TestTaskReport work() {
		TestListener listener = new TestListener();
		JUnitCore junit = new JUnitCore();
		junit.addListener(listener);
		junit.run(test);
		return new TestTaskReport(listener.getReport());
	}

}
