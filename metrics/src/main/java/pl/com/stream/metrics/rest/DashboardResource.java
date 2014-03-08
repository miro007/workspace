package pl.com.stream.metrics.rest;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.DashboardEvent;
import pl.com.stream.metrics.repo.DashboardEventRepository;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.service.AccountService;
import pl.com.stream.metrics.service.UserService;

@RestController
@RequestMapping("/rest/dashboards")
public class DashboardResource {
	@Inject
	DashboardRepository repo;
	@Inject
	DashboardEventRepository dashboardEventRepository;
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

	@RequestMapping(value = "/{id}/event", method = RequestMethod.POST)
	public void addEvent(@PathVariable Long id, @RequestBody DashboardEvent event) {
		Dashboard dashboard = repo.findOne(id);
		event.setDashboard(dashboard);
		event.setDate(new Date());
		dashboardEventRepository.save(event);
	}

	@RequestMapping(value = "/{id}/event", method = RequestMethod.GET)
	public List<DashboardEvent> listEvent(@PathVariable("id") Long id) {
		Dashboard dashboard = repo.findOne(id);
		return dashboardEventRepository.findByDashboard(dashboard);
	}

	@RequestMapping(value = "/{id}/event", method = RequestMethod.DELETE)
	public void removeEvent(@PathVariable Long id, @RequestParam("idEvent") Long idEvent) {
		dashboardEventRepository.delete(idEvent);
	}

}
