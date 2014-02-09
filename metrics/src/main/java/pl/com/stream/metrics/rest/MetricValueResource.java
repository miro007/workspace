package pl.com.stream.metrics.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;
import pl.com.stream.metrics.service.MetricService;

@RestController
@RequestMapping("/rest/metricsValue")
public class MetricValueResource {
	@Inject
	MetricValueRepository repo;

	@Inject
	MetricRepository metricRepository;
	@Inject
	MetricService metricService;

	@RequestMapping(method = RequestMethod.GET)
	public List<MetricValue> query(@RequestParam("idMetric") Long idMetric) {
		Metric metric = metricRepository.findOne(idMetric);

		return repo.findByMetric(metric);
	}
	
}
