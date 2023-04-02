package orbartal.springboottester.testtask.worker;

import orbartal.springboottester.task.model.TaskReport;

public interface TaskWorker <R extends TaskReport> {

	public R work();

}
