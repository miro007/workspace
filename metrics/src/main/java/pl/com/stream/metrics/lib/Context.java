package pl.com.stream.metrics.lib;

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
		if (repo.count() == 0) {
			Account account = repo.findOne(1L);
			if (account == null) {
				account = new Account("a", "b");
				repo.save(account);
			}

			for (int i = 0; i < 10; i++) {
				Dashboard dashboard = dashboardRepository.findOne(1L);
				dashboard = new Dashboard(i + " " + System.currentTimeMillis());
				dashboard.setAccount(account);
				dashboardRepository.save(dashboard);
				for (int k = 0; k < 10; k++) {
					Metric metric = metricRepository.findOne(1L);
					metric = new Metric("" + i + k + System.currentTimeMillis());
					metric.setDashboard(dashboard);
					metricRepository.save(metric);
				}
			}
		}
	}
}
