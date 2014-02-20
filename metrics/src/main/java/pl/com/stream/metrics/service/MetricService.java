package pl.com.stream.metrics.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricLinkType;
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

	public Long save(Long idDashboard, String name, String link) {
		Metric metric = new Metric(name);
		metric.setDashboard(dashboardRepository.findOne(idDashboard));
		metric.setPullLink(link);
		repo.save(metric);
		return metric.getId();
	}

	public Long save(Long idDashboard, String name) {
		return save(idDashboard, name, null);
	}

	public void addValue(Long idMetric, Double value) {
		Metric metric = repo.findOne(idMetric);

		MetricValue metricVal = new MetricValue();
		metricVal.setMetric(metric);
		metricVal.setDate(new Date());
		metricVal.setValue(value);

		metricValueRepository.save(metricVal);
	}

	public void update(Long id, String name, String link) {
		Metric metric = repo.findOne(id);
		metric.setName(name);
		metric.setPullLink(link);
		repo.save(metric);
	}

	@Async
	public Future<Void> addValue(String dashboardName, String metricName, Double value) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long idAccount = userService.getIdAccount();
		Account account = accountRepository.findOne(idAccount);
		Dashboard dashboard = dashboardRepository.findByAccountAndName(account, dashboardName);
		if (dashboard == null) {
			dashboard = new Dashboard(dashboardName);
			dashboard.setAccount(account);
			dashboardRepository.save(dashboard);
		}

		Metric metric = repo.findByDashboardAndName(dashboard, metricName);
		if (metric == null) {
			metric = new Metric(metricName);
			metric.setDashboard(dashboard);

			repo.save(metric);
		}

		MetricValue metricValue = new MetricValue();
		metricValue.setMetric(metric);
		metricValue.setValue(value);

		metricValueRepository.save(metricValue);
		return new AsyncResult<Void>(null);
	}

	public void delete(Long id) {
		Metric metric = repo.findOne(id);
		List<MetricValue> list = metricValueRepository.findByMetricOrderByDateAsc(metric);
		metricValueRepository.delete(list);
		repo.delete(metric);

	}

	public void save(Long idDashboard, Metric metric) {
		if (metric.getType() == MetricLinkType.PUSH) {
			metric.setPullLink(null);
		}
		metric.setDashboard(dashboardRepository.findOne(idDashboard));
		repo.save(metric);
	}
}
