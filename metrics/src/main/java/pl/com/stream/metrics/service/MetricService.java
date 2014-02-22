package pl.com.stream.metrics.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public static int MAX_PACK_SZIE = 1000;

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

	public Long addValue(Long idMetric, Double value) {
		Metric metric = repo.findOne(idMetric);

		MetricValue metricVal = new MetricValue();
		metricVal.setMetric(metric);
		metricVal.setDate(new Date());
		metricVal.setValue(value);

		metricValueRepository.save(metricVal);
		return metricVal.getId();
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

	@PersistenceContext
	EntityManager em;

	public Collection<MetricValue> findValues(Long idMetric, Date start, Date end) {
		Query query = getMetricValue(idMetric, start, end);
		List<MetricValue> resultList = query.getResultList();
		if (resultList.size() < MAX_PACK_SZIE) {
			return resultList;
		}

		Map<Date, MetricValue> values = new TreeMap<Date, MetricValue>();
		for (MetricValue mv : resultList) {
			values.put(mv.getDate(), mv);
		}
		while (values.size() > MAX_PACK_SZIE) {
			connectValues(values);
		}

		for (MetricValue mv : values.values()) {
			System.out.println(mv);
		}
		return values.values();

	}

	public static void main(String[] args) {
		System.out.println(new Date(1393094211748L));
		System.out.println(new Date(1393099532917L));

	}

	private void connectValues(Map<Date, MetricValue> resultList) {
		Map<Date, MetricValue> transform = new TreeMap<Date, MetricValue>();

		Iterator<Entry<Date, MetricValue>> iterator = resultList.entrySet().iterator();
		while (iterator.hasNext()) {
			try {
				MetricValue val1 = iterator.next().getValue();
				MetricValue val2 = iterator.next().getValue();
				Long d1 = val1.getDate().getTime();
				Double v1 = val1.getValue();

				Long d2 = val2.getDate().getTime();
				Double v2 = val2.getValue();

				MetricValue metricValue = new MetricValue(new Date((d1 + d2) / 2), (v1 + v2) / 2);
				transform.put(metricValue.getDate(), metricValue);
			} catch (Exception e) {
			}
		}
		resultList.clear();
		resultList.putAll(transform);
	}

	private Query getMetricValue(Long idMetric, Date start, Date end) {
		Map<String, Object> map = new HashMap<String, Object>();
		String qlString = "SELECT new MetricValue(date,value) FROM MetricValue WHERE metric.id = :idMetric";
		map.put("idMetric", idMetric);
		if (start != null) {
			qlString += " and date >= :start";
			map.put("start", start);
		}

		if (end != null) {
			qlString += " and date <= :end";
			map.put("end", end);
		}
		qlString += " ORDER BY date ASC";
		Query query = em.createQuery(qlString);
		for (String name : map.keySet()) {
			query.setParameter(name, map.get(name));
		}
		return query;
	}
}
