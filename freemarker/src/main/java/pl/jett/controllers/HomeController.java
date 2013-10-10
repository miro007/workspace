
package pl.jett.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.jett.domain.User;

@Controller
public class HomeController extends AbstractController {

	@RequestMapping(value = {
		"/",
		"/index.html"
	})
	public String index(final Model model) {
		model.addAttribute("user", new User("miro"));
		// log.info("/index.htm ");
		return "home/index";
	}

	@RequestMapping("/protected.html")
	public String protectedPage() {
		log.info("/protected.htm ");
		return "home/protected";
	}

	public String hello() {
		return "miro";
	}
}
