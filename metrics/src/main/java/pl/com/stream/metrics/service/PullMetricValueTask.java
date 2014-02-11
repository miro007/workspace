package pl.com.stream.metrics.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.repo.MetricRepository;

@Service
public class PullMetricValueTask {
	@Inject
	MetricRepository metricRepository;
	@Inject
	MetricService metricService;

	@Scheduled(fixedRate = 10000)
	public void getValues() {
		RestTemplate restTemplate = new RestTemplate();
		List<Metric> metrics = metricRepository.findByPullLinkIsNotNull();
		for (Metric metric : metrics) {
			Double value = restTemplate.getForObject(metric.getPullLink(), Double.class);
			metricService.addValue(metric.getId(), value);
		}
	}
}
