package orbartal.springboottester.demo;

public class UrlProvider {

	private int port = 8080;

	public String buildUrlDemo() {
		return "http://localhost:" + port + "/api/demo";
	}

	public String buildUrlDemoDelete(String key) {
		return "http://localhost:" + port + "/api/demo" + "/" + key;
	}

}
