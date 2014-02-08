package pl.com.stream.metrics;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.fest.assertions.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.config.Application;
import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.repo.AccountRepository;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
// @ActiveProfiles("dev")
@Transactional
public class AccountServiceTest {
	@Inject
	AccountService service;
	@Inject
	AccountRepository repository;
	@Inject
	DashboardRepository dashboardRepository;

	@Test
	public void shouldCreateAccount() {
		assertThat(repository.count()).isZero();
		// given
		String email = "demo@tlen.pl";
		service.createAccount(email, email);

		// when
		Account account = repository.findByEmail(email);

		// then
		assertThat(account).isNotNull();
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldNotCreateTwoAccountWithTheSameEmail() {
		assertThat(repository.count()).isZero();
		// given
		String email = "demo@tlen.pl";
		service.createAccount(email, email);

		// when
		service.createAccount(email, email);

		// then
		Fail.fail("exception didn't occur");
	}

	@Test
	public void shouldAddDashboardToAccount() {
		// given
		assertThat(repository.count()).isZero();
		String email = "demo@tlen.pl";
		Long createAccount = service.createAccount(email, email);

		// when
		service.saveDashboard(createAccount, "nazwa");

		// then
		assertThat(repository.findByEmail(email).getDashboardSet()).hasSize(1);
	}

	@Test
	public void shouldManageDashboard() {
		// given
		assertThat(repository.count()).isZero();
		String email = "demo@tlen.pl";
		Long createAccount = service.createAccount(email, email);

		// when
		Long dashboard = service.saveDashboard(createAccount, "nazwa");

		// then
		assertThat(repository.findByEmail(email).getDashboardSet()).hasSize(1);

		// when
		String name = "second";
		service.updateDashboard(dashboard, name);

		// then
		assertThat(dashboardRepository.findOne(dashboard).getName()).isEqualTo(
				name);

		// when
		service.deleteDashboard(dashboard);

		// then
		assertThat(dashboardRepository.findOne(dashboard)).isNull();
		assertThat(repository.findOne(createAccount).getDashboardSet())
				.hasSize(0);
	}

	@Test
	public void shouldRemoveAccount() {
		assertThat(repository.count()).isZero();
		// given
		String email = "demo@tlen.pl";
		Long createAccount = service.createAccount(email, email);
		service.saveDashboard(createAccount, "nazwa");

		// when
		service.deleteAccount(createAccount);

		// then
		assertThat(repository.findByEmail(email)).isNull();
	}
}
