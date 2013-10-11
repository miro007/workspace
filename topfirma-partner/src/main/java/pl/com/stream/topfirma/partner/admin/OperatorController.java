
package pl.com.stream.topfirma.partner.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.com.stream.topfirma.partner.UserContext;
import pl.com.stream.topfirma.partner.admin.repo.Operator;
import pl.com.stream.topfirma.partner.admin.repo.OperatorRepository;

@Controller
@RequestMapping("/admin")
public class OperatorController {

	@Autowired
	UserContext userContext;
	@Autowired
	OperatorRepository operatorRepository;

	@RequestMapping("/operator/{id}")
	public String loginPage(final Model model, @PathVariable("id") final Long id) {
		if (id != null) {
			model.addAttribute("operator", operatorRepository.find(id));
		}
		return "admin/operator";
	}

	@RequestMapping("/operator")
	public String loginPage(final Model model) {
		model.addAttribute("operator", new Operator());
		return loginPage(model, null);
	}

}
