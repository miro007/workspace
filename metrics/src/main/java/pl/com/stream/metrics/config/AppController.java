package pl.com.stream.metrics.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/app")
	public String reditrect() {
		return "redirect:static/app/index.html";
	}

}
