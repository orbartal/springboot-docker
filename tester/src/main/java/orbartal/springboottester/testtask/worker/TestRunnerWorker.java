package orbartal.springboottester.testtask.worker;

import orbartal.springboottester.test.runner.TestBeanRunner;
import orbartal.springboottester.test.runner.TestListener;
import orbartal.springboottester.testtask.model.TestTaskReport;

public class TestRunnerWorker implements TaskWorker<TestTaskReport> {

	private final JunitRunner runner;

	public TestRunnerWorker(TestBeanRunner test) {
		this.runner = new JunitRunner(test);
	}

	@Override
	public TestTaskReport work() {
		TestListener listener = new TestListener();
		runner.run(listener);
		return new TestTaskReport(listener.getReport());
	}

}
