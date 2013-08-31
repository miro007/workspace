
package pl.com.stream.rdp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.com.stream.rdp.service.MemberDto;
import pl.com.stream.rdp.service.MemberService;

@Controller
@RequestMapping(value = "/member", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

	@Inject
	MemberService service;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(final @RequestBody MemberDto dto) {
		service.save(dto);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public MemberDto login(final HttpSession session, final @RequestParam("email") String email) {
		MemberDto memberDto = service.findByEmail(email);
		if (memberDto != null) {
			session.setAttribute("user", email);
		}
		return memberDto;
	}

}
