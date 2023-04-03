package orbartal.springboottester.demo.test;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import com.google.gson.Gson;

import orbartal.springboottester.demo.DemoDto;
import orbartal.springboottester.demo.util.DemoDtoFactory;
import orbartal.springboottester.demo.util.DemoUrlProvider;

@TestMethodOrder(OrderAnnotation.class)
public class CrudMultiErrorTest {

	private static final String KEY_A1 = "ka1";
	private static final String VALUE_A1 = "va1";
	private static final String KEY_B1 = "kb1";
	private static final String VALUE_B1 = "vb1";
	private static final String KEY_C1 = "kc1";
	private static final String VALUE_C1 = "vc1";
	private static final String KEY_D1 = "kd1";
	private static final String VALUE_D1 = "vd1";

	private final DemoUrlProvider urlProvider = new DemoUrlProvider();

	private final Gson gson = new Gson();
	
	//Part 1: clean and validate initial state before the test stare

	@Order(0)
	@Test
	public void test00DeleteAllStart() throws Exception {
		String url = urlProvider.buildUrlDemo();
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .DELETE()
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
	}

	@Order(1)
	@Test
	public void test01GetAllEmptyStart() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertEquals("[]", response.body());
	}
	
	//Part 2: Prepare state and validate without error

	@Order(2)
	@Test
    public void test02CreateDemoA() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(KEY_A1, VALUE_A1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }
	
	@Order(3)
	@Test
    public void test03CreateDemoB() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(KEY_B1, VALUE_B1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }
	
	@Order(4)
	@Test
    public void test04CreateDemoC() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(KEY_C1, VALUE_C1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_CREATED, response.statusCode());
    }

	@SuppressWarnings("rawtypes")
	@Order(5)
	@Test
    public void test05GetAllDemosAfterCreate() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		List output = gson.fromJson(response.body(), List.class);

		Assertions.assertNotNull(output);
		Assertions.assertEquals(3, output.size());

		Map aOut =  (Map) output.get(0);
		Assertions.assertEquals(KEY_A1, aOut.get("key"));
		Assertions.assertEquals(VALUE_A1, aOut.get("value"));
		
		Map bOut =  (Map) output.get(1);
		Assertions.assertEquals(KEY_B1, bOut.get("key"));
		Assertions.assertEquals(VALUE_B1, bOut.get("value"));
		
		Map cOut =  (Map) output.get(2);
		Assertions.assertEquals(KEY_C1, cOut.get("key"));
		Assertions.assertEquals(VALUE_C1, cOut.get("value"));
    }

	//Part 3: Request that should result in error

	@Order(6)
	@Test
    public void test06GetDemoError() throws Exception {
		String url = urlProvider.buildUrlDemo() + "/" + KEY_D1;
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, response.statusCode());
    }

	@Order(7)
	@Test
    public void test07ReCreateDemoError() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(KEY_C1, VALUE_C1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, response.statusCode());
    }

	@Order(8)
	@Test
    public void test08UpdateDemoDError() throws Exception {
		String url = urlProvider.buildUrlDemo();
		DemoDto input = DemoDtoFactory.buildDemoDto(KEY_D1, VALUE_D1);
		String requestBody = gson.toJson(input);

        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, response.statusCode());
    }

	@Order(9)
	@Test
	 public void test09DeleteDemoD() throws Exception {
		String url = urlProvider.buildUrlDemo() + "/" + KEY_D1;
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .DELETE()
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, response.statusCode());
    }

	@SuppressWarnings("rawtypes")
	@Order(10)
	@Test
    public void test10GetAllDemosAfterErrors() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		List output = gson.fromJson(response.body(), List.class);

		Assertions.assertNotNull(output);
		Assertions.assertEquals(3, output.size());

		Map aOut =  (Map) output.get(0);
		Assertions.assertEquals(KEY_A1, aOut.get("key"));
		Assertions.assertEquals(VALUE_A1, aOut.get("value"));
		
		Map bOut =  (Map) output.get(1);
		Assertions.assertEquals(KEY_B1, bOut.get("key"));
		Assertions.assertEquals(VALUE_B1, bOut.get("value"));
		
		Map cOut =  (Map) output.get(2);
		Assertions.assertEquals(KEY_C1, cOut.get("key"));
		Assertions.assertEquals(VALUE_C1, cOut.get("value"));
    }
	
	//Part 4: reset and validate to end the test in a clean state
	
	@Order(11)
	@Test
	public void test11DeleteAllDemoEnd() throws Exception {
		String url = urlProvider.buildUrlDemo();
        HttpRequest request = HttpRequest.newBuilder()
        	.uri(new URI(url))
            .headers("Content-Type", "application/json")
            .DELETE()
            .build();

		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
	}
	
	@Order(12)
	@Test
	public void test12GetAllDemoEmptyEnd() throws Exception {
		String url = urlProvider.buildUrlDemo();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).GET().build();
		BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

		HttpResponse<String> response = HttpClient.newBuilder().build().send(request, handler);

		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertEquals("[]", response.body());
	}

}