package pl.com.stream.metrics.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.repo.AccountRepository;
import pl.com.stream.metrics.repo.DashboardRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {
	@Inject
	AccountRepository accountRepository;
	@Inject
	DashboardRepository dashboardRepository;

	public Long createAccount(String email, String password) {
		Account account = accountRepository.save(new Account(email, password));
		accountRepository.save(account);
		return account.getId();
	}

	public Long saveDashboard(Long idAccount, String name) {
		Account account = accountRepository.findOne(idAccount);
		Dashboard dashboard = new Dashboard(name);
		account.addDashboard(dashboard);
		return dashboard.getId();
	}

	public void updateDashboard(Long idDashboard, String name) {
		Dashboard dashboard = dashboardRepository.findOne(idDashboard);
		dashboard.setName(name);
		dashboardRepository.save(dashboard);
	}

	public void deleteDashboard(Long id) {
		dashboardRepository.delete(id);
	}

	public void deleteAccount(Long idAccount) {
		accountRepository.delete(idAccount);
	}

}
