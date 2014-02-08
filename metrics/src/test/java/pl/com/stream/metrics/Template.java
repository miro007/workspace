package pl.com.stream.metrics;

import org.springframework.web.client.RestTemplate;

public class Template {

	public static void main(String[] args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String forObject = restTemplate.getForObject(
				"http://localhost:8080/miro/app/rest/miro", String.class);
		System.out.println(forObject);

	}

}
