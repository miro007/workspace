package pl.com.stream.metrics.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.repo.AccountRepository;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.service.MetricService;

@RestController
@RequestMapping("/rest/dashboards/{idDashboard}/metrics")
public class MetricResource {
	@Inject
	AccountRepository accountRepository;
	@Inject
	MetricRepository repo;
	@Inject
	DashboardRepository dashboardRepository;
	@Inject
	MetricService metricService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Metric> queryByDashboard(@PathVariable Long idDashboard) {
		return repo.findByDashboard(dashboardRepository.findOne(idDashboard));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Metric find(@PathVariable Long id) {
		return repo.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void save(@PathVariable Long idDashboard, @RequestBody Metric metric) {
		if (metric.getId() != null) {
			metricService.update(metric.getId(), metric.getName());
		} else {
			metricService.save(idDashboard, metric.getName());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		repo.delete(id);
	}

}
