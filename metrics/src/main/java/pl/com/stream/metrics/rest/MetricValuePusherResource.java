package pl.com.stream.metrics.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;
import pl.com.stream.metrics.service.MetricService;

@RestController
@RequestMapping("/rest/pushMetricValue")
public class MetricValuePusherResource {
	@Inject
	MetricValueRepository repo;

	@Inject
	MetricRepository metricRepository;
	@Inject
	MetricService metricService;

	@RequestMapping(value = "addMetricValue", method = RequestMethod.GET)
	public void add(@RequestParam Long idMetric, @RequestParam Double value) {
		metricService.addValue(idMetric, value);
	}

	@RequestMapping(value = "addDynamicValue", method = RequestMethod.GET)
	public void add(@RequestParam String dashboardCode,
			@RequestParam String metricCode, @RequestParam Double value) {
		metricService.addValue(dashboardCode, metricCode, value);
	}
}
