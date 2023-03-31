package orbartal.springboottester.tasktask.worker;

import orbartal.springboottester.task.model.TaskReport;

public interface TaskWorker <R extends TaskReport> {

	public R work();

}
