package pl.com.stream.metrics.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.repo.AccountRepository;

@Service
public class UserService {
	@Inject
	AccountRepository accountRepository;

	public Long getIdAccount() {
		return 1L;
	}

	public Account getAccount() {
		return accountRepository.findOne(getIdAccount());
	}
}
