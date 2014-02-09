package pl.com.stream.metrics.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
import pl.com.stream.metrics.repo.AccountRepository;
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

	@Inject
	UserService userService;
	@Inject
	AccountRepository accountRepository;

	public Long save(Long idDashboard, String name) {
		Metric metric = new Metric(name);
		metric.setDashboard(dashboardRepository.findOne(idDashboard));
		repo.save(metric);
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

	public void addValue(String code, Double value) {
		Metric metric = repo.findByName(code);

		MetricValue metricVal = new MetricValue();
		metricVal.setMetric(metric);
		metricVal.setDate(new Date());
		metricVal.setValue(value);

		metricValueRepository.save(metricVal);
	}

	public void update(Long id, String name) {
		Metric metric = repo.findOne(id);
		metric.setName(name);
		repo.save(metric);
	}

	public void addValue(String dashboardName, String metricName, Double value) {
		Dashboard dashboard = dashboardRepository.findByName(metricName);
		if (dashboard == null) {
			dashboard = new Dashboard(dashboardName);
			dashboardRepository.save(dashboard);
		}

		Metric metric = repo.findByName(metricName);
		if (metric == null) {
			metric = new Metric(metricName);
		}
		metric.setDashboard(dashboard);
		repo.save(metric);
		MetricValue metricValue = new MetricValue();
		metricValue.setMetric(metric);
		metricValue.setValue(value);

		metricValueRepository.save(metricValue);

	}
}
