package orbartal.springboottester.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbartal.springboottester.example.application.ExampleTestApp;
import orbartal.springboottester.task.api.model.TaskCreateResponseDto;

@RestController
@RequestMapping(path = "/api/v1/test/executer")
public class TestExampleController {

	@Autowired
	private ExampleTestApp executer;

	@PostMapping(path = "/example/test_4_results", produces = "application/json")
	public TaskCreateResponseDto test4Results() {
		return executer.test4Results();
	}

}
