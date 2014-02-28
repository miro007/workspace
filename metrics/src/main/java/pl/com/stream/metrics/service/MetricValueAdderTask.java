package pl.com.stream.metrics.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;

@Service
public class MetricValueAdderTask {
	@Inject
	MetricRepository metricRepository;

	@Inject
	MetricValueRepository metricValueRepository;
	Calendar inCalendar = Calendar.getInstance();
	Random r = new Random(10000);

	@Scheduled(fixedRate = 60000)
	public void addValue() {
		Iterable<Metric> findAll = metricRepository.findAll();
		for (Metric m : findAll) {
			MetricValue metricValue = new MetricValue();
			metricValue.setMetric(m);
			metricValue.setDate(new Date());
			metricValue.setValue(r.nextDouble());
			metricValueRepository.save(metricValue);
		}
	}
}
