package pl.com.stream.metrics.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;

import pl.com.stream.metrics.websocket.DefaultEchoService;
import pl.com.stream.metrics.websocket.EchoWebSocketHandler;

public class HelloWebXml extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		super.onStartup(servletContext);
	}

	@Bean(name = "/echo")
	public WebSocketHandler echoWebSocketHandler() {
		return new EchoWebSocketHandler(new DefaultEchoService("as"));
	}
}
