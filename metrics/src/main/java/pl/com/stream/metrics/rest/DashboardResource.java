package pl.com.stream.metrics.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.service.AccountService;
import pl.com.stream.metrics.service.UserService;

@RestController
@RequestMapping("/rest/dashboards")
public class DashboardResource {
	@Inject
	DashboardRepository repo;
	@Inject
	AccountService service;
	@Inject
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Dashboard> query() {
		return repo.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Dashboard find(@PathVariable Long id) {
		return repo.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody Dashboard dashboard) {
		if (dashboard.getId() != null) {
			service.updateDashboard(dashboard.getId(), dashboard.getName());
		} else {
			service.saveDashboard(userService.getIdAccount(), dashboard.getName());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.deleteDashboard(id);
	}

}
