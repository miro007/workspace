package pl.com.stream.metrics.config;

import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();
		Object value = restTemplate
				.getForObject(
						"http://10.90.13.12:8180/app/monitoring?&part=lastValue&graph=httpSessions",
						String.class);
		System.out.println(Double.parseDouble(value.toString()));

	}
}
