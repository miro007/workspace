package pl.com.stream.rdp.controller;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.com.stream.rdp.repo.MeetingRepository;

@Controller
public class HomeController {

	@Inject
	MeetingRepository meetingRepository;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String find(final Model model) {
		model.addAttribute("meetings",
				meetingRepository.findAll(new PageRequest(0, 10)));
		return "index";
	}

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public String app() {
		return "angular/app";
	}

}
