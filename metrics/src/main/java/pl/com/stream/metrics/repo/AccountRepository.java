package pl.com.stream.metrics.repo;

import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByEmail(String email);
}
