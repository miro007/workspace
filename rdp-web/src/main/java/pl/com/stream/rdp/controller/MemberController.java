package pl.com.stream.rdp.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, produces = MediaType.ALL_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void login(final HttpSession session, HttpServletResponse response,
			@RequestBody MemberDto dto) {
		service.login(dto);
		session.setAttribute("user", dto.getEmail());
		response.addCookie(new Cookie("email", dto.getEmail()));

	}

	@RequestMapping(value = "/isLogged", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public ResponseEntity<Boolean> isLogged(HttpServletRequest request,
			final HttpSession session) {
		String email = getCookieValue(request, "email");
		if (email != null) {
			session.setAttribute("user", email);
		}
		return new ResponseEntity<Boolean>(
				session.getAttribute("user") != null, HttpStatus.OK);
	}

	private String getCookieValue(HttpServletRequest request, String string) {
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals(string)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
