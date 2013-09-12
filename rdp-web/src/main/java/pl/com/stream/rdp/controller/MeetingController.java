package pl.com.stream.rdp.controller;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.com.stream.rdp.model.Meeting;
import pl.com.stream.rdp.repo.MeetingRepository;
import pl.com.stream.rdp.service.MeetingDto;
import pl.com.stream.rdp.service.MeetingService;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 
 * @author Mirek23
 * 
 */
@Controller
@RequestMapping(value = "/meeting", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
public class MeetingController {

	@Inject
	MeetingService service;
	@Inject
	MeetingRepository meetingRepository;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.ALL_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void save(final MeetingDto dto) {
		service.save(dto);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.ALL_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void saveJSON(final @RequestBody MeetingDto dto) {
		service.save(dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MeetingDto find(final @PathVariable("id") String id) {
		return service.find(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.ALL_VALUE)
	public String list(final Model model) {
		List<Meeting> meetings = meetingRepository
				.findByDevOrderByCreationDateAsc(true);
		model.addAttribute("meetings", meetings);
		return "list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public List<MeetingDto> list() {
		List<Meeting> meetings = meetingRepository
				.findByDevOrderByCreationDateAsc(true);
		List<MeetingDto> transform = Lists.transform(meetings,
				new Function<Meeting, MeetingDto>() {

					@Override
					public MeetingDto apply(@Nullable Meeting input) {
						return service.find(input.getId());
					}
				});
		return transform;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(final @PathVariable("id") String id) {
		meetingRepository.delete(id);
	}

	@RequestMapping(value = "/{id}/addMember", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void addMember(@PathVariable("id") String id,
			final HttpSession session) {
		service.addMember(id, (String) session.getAttribute("user"));
	}

	@RequestMapping(value = "/{id}/removeMember", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void removeMember(@PathVariable("id") String id,
			final HttpSession session) {
		service.removeMember(id, (String) session.getAttribute("user"));
	}
}
