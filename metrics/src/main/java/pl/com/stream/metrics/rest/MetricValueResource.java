package pl.com.stream.metrics.rest;

import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Collection<MetricValue> query(
			@RequestParam("idMetric") Long idMetric,
			@RequestParam(value = "start", required = false) Date start,
			@RequestParam(value = "end", required = false) Date end) {

		return metricService.findValues(idMetric, start, end);
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
