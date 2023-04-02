package orbartal.springboottester.testtask.worker;

import org.junit.runner.JUnitCore;

import orbartal.springboottester.test.runner.TestListener;
import orbartal.springboottester.testtask.model.TestTaskReport;

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
