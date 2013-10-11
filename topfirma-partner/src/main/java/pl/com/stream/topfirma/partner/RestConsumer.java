
package pl.com.stream.topfirma.partner;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestConsumer {

	private String cookieId;
	private final RestTemplate restTemplate;

	public RestConsumer(final String cookieId) {
		this.cookieId = cookieId;
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
	}

	public <R> R execute(final String url, final Object object, final Class<R> responseClass) {
		return execute(url, HttpMethod.GET, object, responseClass);
	}

	public <R> R execute(final String url, final HttpMethod method, final Object object, final Class<R> responseClass) {
		HttpHeaders requestHeaders = new HttpHeaders();
		if (cookieId != null) {
			requestHeaders.add("Cookie", "JSESSIONID=" + cookieId);
		}
		HttpEntity requestEntity = new HttpEntity(object, requestHeaders);
		ResponseEntity rssResponse = restTemplate.exchange(url, method, requestEntity, responseClass);

		if (rssResponse.getHeaders().get("Set-Cookie") != null) {
			String text = rssResponse.getHeaders().get("Set-Cookie").toString();
			this.cookieId = text.substring(text.indexOf("JSESSIONID=") + "JSESSIONID=".length(), text.indexOf(";"));
		}
		return (R) rssResponse.getBody();
	}

	public static void main(final String[] args) {
		RestConsumer restConsumer = new RestConsumer(null);
		Object o =
				restConsumer.execute(
						"http://topfirma-dev/topfirma-www/services/rest/operator/login?email=m.szajowski@streamsoft.pl&password=mirek",
						null, Map.class);
		System.out.println(o);
		o = restConsumer.execute("http://topfirma-dev/topfirma-www/services/rest/operator/list", null, Map.class);
		System.out.println(o);
	}

}
