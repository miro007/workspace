package pl.com.stream.metrics.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.model.Dashboard;

public interface DashboardRepository extends CrudRepository<Dashboard, Long> {

	List<Dashboard> findByAccount(Account account);

	Dashboard findByName(String dashboardCode);
}
