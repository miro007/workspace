package pl.com.stream.metrics.rest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

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
@RequestMapping("/rest/metrics/values")
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

	Random random = new Random(100);

	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public Double randomVal() {
		return random.nextDouble();
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(@RequestParam Long idMetric, @RequestParam Double value) {
		metricService.addValue(idMetric, value);
	}

	@RequestMapping(value = "/addByName", method = RequestMethod.GET)
	public void add(String dashboardName, @RequestParam String metricName,
			@RequestParam Double value) {
		Future<Void> addValue = metricService.addValue(dashboardName,
				metricName, value);
		System.out.println(addValue);
	}
}
