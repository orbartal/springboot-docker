package orbartal.springboottester.testtask.worker;

import org.junit.runner.JUnitCore;

import orbartal.springboottester.test.runner.TestListener;
import orbartal.springboottester.testtask.model.TestTaskReport;
import org.junit.runner.Request;

public class RequestTestWorker implements TaskWorker<TestTaskReport> {

	private final Class<?> junitTestClass;

	public RequestTestWorker(Class<?> junitTestClass) {
		this.junitTestClass = junitTestClass;
	}

	@Override
	public TestTaskReport work() {
		//TODO: improve
		Request request = Request.aClass(junitTestClass).sortWith((t1, t2)->t1.getMethodName().compareTo(t2.getMethodName()));
		TestListener listener = new TestListener();
		JUnitCore junit = new JUnitCore();
		junit.addListener(listener);
		junit.run(request);
		return new TestTaskReport(listener.getReport());
	}

}
