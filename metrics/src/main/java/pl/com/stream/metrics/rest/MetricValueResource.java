package pl.com.stream.metrics.rest;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
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

	Random random = new Random();

	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public Double randomVal() {
		return random.nextDouble();
	}

}
