package pl.com.stream.metrics.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.DashboardEvent;

public interface DashboardEventRepository extends CrudRepository<DashboardEvent, Long> {

	List<DashboardEvent> findByDashboard(Dashboard dashboard);

}
