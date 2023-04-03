package orbartal.springboottester.demo;

public class UrlProvider {

	private final String demoUrl = DemoProptiesUtil.getDemoUrl();

	public String buildUrlDemo() {
		return demoUrl + "/api/demo";
	}

	public String buildUrlDemoDelete(String key) {
		return demoUrl + "/api/demo" + "/" + key;
	}

}
