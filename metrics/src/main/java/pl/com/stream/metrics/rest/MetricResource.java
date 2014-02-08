package pl.com.stream.metrics.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;

@RestController
@RequestMapping("/rest/metrics")
public class MetricResource {
	@Inject
	MetricRepository repo;
	@Inject
	DashboardRepository dashboardRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Metric> query(@RequestParam Long idDashboard) {
		return repo.findByDashboard(dashboardRepository.findOne(idDashboard));
	}
}
