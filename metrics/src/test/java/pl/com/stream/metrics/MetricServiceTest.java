package pl.com.stream.metrics;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.config.Application;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;
import pl.com.stream.metrics.service.AccountService;
import pl.com.stream.metrics.service.MetricService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
// @ActiveProfiles("dev")
@Transactional
public class MetricServiceTest {
	@Inject
	AccountService service;

	@Inject
	MetricService metricService;
	@Inject
	DashboardRepository dashboardRepository;
	@Inject
	MetricRepository metricRepository;
	@Inject
	MetricValueRepository metricValueRepository;

	@Test
	public void shouldCreateMetricWithNameAndCode() {
		// given
		String email = "demo@tlen.pl";
		Long createAccount = service.createAccount(email, email);

		Long idDashboard = service.saveDashboard(createAccount, "nazwa");

		// when
		Long idMetric = metricService.save(idDashboard, "name");

		// then
		assertThat(dashboardRepository.findOne(idDashboard).getMetricSet())
				.hasSize(1);
	}

	@Test
	public void shouldGenerateLinkForPush() {

	}

	@Test
	public void shouldSetLinkForPoll() {

	}

	@Test
	public void shouldAllowTestPoll() {

	}

	@Test
	public void shouldSaveValue() {
		// given
		String email = "demo@tlen.pl";
		Long createAccount = service.createAccount(email, email);

		Long idDashboard = service.saveDashboard(createAccount, "nazwa");
		Long idMetric = metricService.save(idDashboard, "name");

		// when
		metricService.addValue(idMetric, 1.2);

		// then
		assertThat(
				metricValueRepository.findByMetric(metricRepository
						.findOne(idMetric))).hasSize(1);
	}

	@Test
	public void shouldGetValueFromTime() {

	}

	@Test
	public void shouldGetValueFromTimeToTime() {

	}

	@Test
	public void shouldGetValueFromTimeToTimeAndJoinThemWhenThereIsTooMuchPoints() {

	}
}
