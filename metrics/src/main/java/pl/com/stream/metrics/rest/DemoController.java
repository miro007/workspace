package pl.com.stream.metrics.rest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.repo.AccountRepository;

@RestController
public class DemoController {
	@Autowired
	public AccountRepository repo;

	@RequestMapping(value = "app/rest/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account find(@PathVariable Long id, HttpServletResponse response) {
		return repo.findAll().iterator().next();
	}

	@RequestMapping(value = "app/rest/miro", method = RequestMethod.GET)
	public Object get(HttpServletResponse response) {
		return "super";
	}
}
