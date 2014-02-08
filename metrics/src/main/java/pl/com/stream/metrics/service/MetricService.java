package pl.com.stream.metrics.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class MetricService {
	@Inject
	MetricRepository repo;

	@Inject
	MetricValueRepository metricValueRepository;

	@Inject
	DashboardRepository dashboardRepository;

	public Long save(Long idDashboard, String name, String code) {
		Metric metric = new Metric(name, code);

		Dashboard dashboard = dashboardRepository.findOne(idDashboard);
		dashboard.addMetric(metric);

		return metric.getId();
	}

	public void addValue(Long idMetric, Double value) {
		Metric metric = repo.findOne(idMetric);

		MetricValue metricVal = new MetricValue();
		metricVal.setMetric(metric);
		metricVal.setDate(new Date());
		metricVal.setValue(value);

		metricValueRepository.save(metricVal);
	}
}
