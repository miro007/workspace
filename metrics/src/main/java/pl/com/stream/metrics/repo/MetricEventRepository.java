package pl.com.stream.metrics.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricEvent;

public interface MetricEventRepository extends CrudRepository<MetricEvent, Long> {

	List<MetricEvent> findByMetric(Metric metric);

}
