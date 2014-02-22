package pl.com.stream.metrics.service;

import java.util.Calendar;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

	@Scheduled(fixedRate = 5000)
	public void addValue() {
		inCalendar.add(Calendar.MINUTE, 1);
		MetricValue metricValue = new MetricValue();
		metricValue.setMetric(metricRepository.findOne(1L));
		metricValue.setDate(inCalendar.getTime());
		metricValue.setValue(r.nextDouble());
		metricValueRepository.save(metricValue);
	}
}
