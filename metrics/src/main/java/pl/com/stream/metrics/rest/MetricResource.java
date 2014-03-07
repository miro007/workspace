package pl.com.stream.metrics.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricEvent;
import pl.com.stream.metrics.repo.AccountRepository;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricEventRepository;
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

	@Inject
	MetricEventRepository metricEventRepository;

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
		metricService.save(idDashboard, metric);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		metricService.delete(id);
	}

	@RequestMapping(value = "/checkPullLink", method = RequestMethod.GET)
	public ResponseEntity<String> checkPullLink(@RequestParam String url) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Double forObject = restTemplate.getForObject(url, Double.class);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/event", method = RequestMethod.POST)
	public void addEvent(@PathVariable Long id, @RequestBody MetricEvent event) {
		Metric metric = repo.findOne(id);
		event.setMetric(metric);
		metricService.addEvent(event);
		List<MetricEvent> findByMetric = metricEventRepository.findByMetric(metric);
	}

	@RequestMapping(value = "/{id}/event", method = RequestMethod.GET)
	public List<MetricEvent> listEvent(@PathVariable("id") Long id) {
		Metric metric = repo.findOne(id);
		return metricEventRepository.findByMetric(metric);
	}

	@RequestMapping(value = "/{id}/event", method = RequestMethod.DELETE)
	public void removeEvent(@PathVariable Long id, @RequestParam("idEvent") Long idEvent) {
		metricEventRepository.delete(idEvent);
	}
}
