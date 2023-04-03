package orbartal.springboottester.testtask.worker;

import org.junit.runner.Request;
import org.junit.runner.Runner;

import orbartal.springboottester.test.runner.TestListener;
import orbartal.springboottester.testtask.model.TestTaskReport;

public class RequestTestWorker implements TaskWorker<TestTaskReport> {

	private final JunitRunner runner;

	public RequestTestWorker(Class<?> junitTestClass) {
		Request request = Request.aClass(junitTestClass).sortWith((t1, t2)->t1.getMethodName().compareTo(t2.getMethodName()));
		Runner runner1 = request.getRunner();
		this.runner = new JunitRunner(runner1);
	}

	@Override
	public TestTaskReport work() {
		TestListener listener = new TestListener();
		runner.run(listener);
		return new TestTaskReport(listener.getReport());
	}

}
