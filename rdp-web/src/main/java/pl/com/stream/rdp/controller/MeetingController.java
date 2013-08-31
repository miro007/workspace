
package pl.com.stream.rdp.controller;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.com.stream.rdp.repo.MeetingRepository;
import pl.com.stream.rdp.service.MeetingDto;
import pl.com.stream.rdp.service.MeetingService;

@Controller
@RequestMapping(value = "/meeting", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
public class MeetingController {

	@Inject
	MeetingService service;
	@Inject
	MeetingRepository meetingRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void add(final MeetingDto dto) {
		service.save(dto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String list(final Model model) {
		model.addAttribute("meetings", meetingRepository.findAll(new PageRequest(0, 10)));
		return "list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(final @PathVariable("id") String id) {
		meetingRepository.delete(id);
	}
}
