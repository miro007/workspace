package pl.com.stream.metrics.repo;

import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.Metric;

public interface MetricRepository extends CrudRepository<Metric, Long> {

	Iterable<Metric> findByDashboard(Dashboard findOne);

}
