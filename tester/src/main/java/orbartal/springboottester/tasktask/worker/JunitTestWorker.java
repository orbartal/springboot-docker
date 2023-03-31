package orbartal.springboottester.tasktask.worker;

import org.junit.runner.JUnitCore;

import orbartal.springboottester.tasktask.model.TestTaskReport;
import orbartal.springboottester.test.runner.TestListener;

public class JunitTestWorker implements TaskWorker<TestTaskReport> {

	private final Class<?> junitTestClass;

	public JunitTestWorker(Class<?> junitTestClass) {
		this.junitTestClass = junitTestClass;
	}

	@Override
	public TestTaskReport work() {
		TestListener listener = new TestListener();
		JUnitCore junit = new JUnitCore();
		junit.addListener(listener);
		junit.run(junitTestClass);
		return new TestTaskReport(listener.getReport());
	}

}
