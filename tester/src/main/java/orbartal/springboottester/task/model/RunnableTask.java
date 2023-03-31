package orbartal.springboottester.task.model;

import java.util.Optional;

public interface RunnableTask extends Runnable {

	public String getName();

	public TaskStatusEnum getStatus();

	public Optional<TaskReport> getReport ();

}
