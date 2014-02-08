package pl.com.stream.metrics.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;

@RestController
@RequestMapping("/rest/metricsValue")
public class MetricValueResource {
	@Inject
	MetricValueRepository repo;

	@Inject
	MetricRepository metricRepository;
	@Inject
	DashboardRepository dashboardRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<MetricValuePackWO> query(
			@RequestParam("idDashboard") Long idDashboard) {
		List<MetricValuePackWO> result = new ArrayList<MetricValuePackWO>();
		Iterable<Metric> metrics = metricRepository
				.findByDashboard(dashboardRepository.findOne(idDashboard));

		for (Metric metric : metrics) {
			MetricValuePackWO metricValuePackWO = new MetricValuePackWO();
			metricValuePackWO.setName(metric.getName());
			List<MetricValue> values = repo.findByMetric(metric);
			for (MetricValue value : values) {
				metricValuePackWO.getData().add(
						new MetricValueWO(value.getDate(), value.getValue()));
			}
			result.add(metricValuePackWO);
		}
		return result;
	}
}
