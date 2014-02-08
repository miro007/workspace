package pl.com.stream.metrics.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;

public interface MetricValueRepository extends
		CrudRepository<MetricValue, Long> {

	List<MetricValue> findByMetric(Metric metric);

	@Query("SELECT mv FROM MetricValue mv JOIN  mv.metric m WHERE m.dashboard= ?1")
	List<List<MetricValue>> findByDashboard(Dashboard dashboard);

}
