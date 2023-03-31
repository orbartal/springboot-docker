package orbartal.springboottester.tasktask.worker;

import org.junit.runner.JUnitCore;

import orbartal.springboottester.tasktask.model.TestTaskReport;
import orbartal.springboottester.test.runner.TestBeanRunner;
import orbartal.springboottester.test.runner.TestListener;

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
