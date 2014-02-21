package pl.com.stream.metrics.lib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
import pl.com.stream.metrics.repo.AccountRepository;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;

@Component
public class Context implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	@PersistenceContext
	private EntityManager em;
	private static EntityManager emDelegate;

	public static <I> I getService(Class<I> clazz) {
		return applicationContext.getBean(clazz);
	}

	public static EntityManager getEm() {
		return emDelegate;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		this.emDelegate = em;
	}

	@Inject
	AccountRepository repo;
	@Inject
	DashboardRepository dashboardRepository;
	@Inject
	MetricRepository metricRepository;
	@Inject
	MetricValueRepository metricValueRepository;

	@PostConstruct
	public void initData() {
		Account account = new Account("a", "b");
		repo.save(account);

		for (int i = 0; i < 1; i++) {
			Dashboard dashboard = new Dashboard(i + "");
			dashboard.setAccount(account);
			dashboardRepository.save(dashboard);
			Random random = new Random(1000);
			for (int k = 0; k < 1; k++) {
				Metric metric = new Metric(k + "" + i);
				metric.setDashboard(dashboard);
				metricRepository.save(metric);
				Date start = new Date(new Date().getTime() - 1000000010);
				List<MetricValue> values = new ArrayList<MetricValue>();
				for (int v = 0; v < 100000; v++) {
					MetricValue metricValue = new MetricValue();
					metricValue.setMetric(metric);
					metricValue.setDate(new Date(start.getTime() + 3600000 + v
							* 1000000));
					metricValue.setValue(random.nextDouble());
					values.add(metricValue);
					if (v % 100 == 0) {

					}
				}
				metricValueRepository.save(values);
			}
		}
	}
}
