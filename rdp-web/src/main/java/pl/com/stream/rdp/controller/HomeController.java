package pl.com.stream.rdp.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.com.stream.rdp.repo.MeetingRepository;

@Controller
public class HomeController {

	@Inject
	MeetingRepository meetingRepository;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public String app() {
		return "angular/app";
	}

}
