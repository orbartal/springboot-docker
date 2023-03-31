package orbartal.springboottester.tasktask.model;

import orbartal.springboottester.task.model.TaskReport;
import orbartal.springboottester.test.report.MultiTestsReport;

public class TestTaskReport implements TaskReport {

	private final MultiTestsReport report;

	public TestTaskReport(MultiTestsReport report) {
		this.report = report;
	}

	public MultiTestsReport getReport() {
		return report;
	}

}
