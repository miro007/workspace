package pl.com.stream.metrics.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;

public interface MetricValueRepository extends CrudRepository<MetricValue, Long> {

	List<MetricValue> findByMetricOrderByDateAsc(Metric metric);

}
