
package pl.com.stream.topfirma.partner.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.com.stream.topfirma.partner.UserContext;
import pl.com.stream.topfirma.partner.admin.repo.OperatorRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserContext userContext;
	@Autowired
	OperatorRepository operatorRepository;

	@RequestMapping({
		"/",
		"index"
	})
	public String index(final Model model) {
		model.addAttribute("operators", operatorRepository.findAll(10, 10));
		model.addAttribute("operators", operatorRepository.findAll(10, 10));
		model.addAttribute("operators", operatorRepository.findAll(10, 10));
		model.addAttribute("operators", operatorRepository.findAll(10, 10));
		return "admin/index";
	}

	@RequestMapping("/login")
	public String loginPage(final Model model) {
		Admin admin = new Admin();
		admin.setEmail("miro007@tlen.pl");
		admin.setPassword("miro007@tlen.pl");
		model.addAttribute("admin", admin);
		model.addAttribute("logged", false);
		return "admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAction(@Valid @ModelAttribute("admin") final Admin admin, final BindingResult result) {
		if (result.hasErrors()) {
			return "admin/login";
		} else {
			userContext.setLogin(admin.getEmail());
			return "redirect:index";
		}
	}

}
