package pl.com.stream.metrics;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.config.Application;
import pl.com.stream.metrics.model.MetricValue;
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
	public void shouldCreateMetricWithName() {
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
				metricValueRepository
						.findByMetricOrderByDateAsc(metricRepository
								.findOne(idMetric))).hasSize(1);
	}

	@Test
	public void shouldGetValueFromTime() {
		// given
		Long metric = metricService.save(1L, "new Metric");
		metricService.addValue(metric, 1.0);
		Long addValue = metricService.addValue(metric, 5.0);
		MetricValue metricValue = metricValueRepository.findOne(addValue);
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DAY_OF_MONTH, 2);
		metricValue.setDate(instance.getTime());

		// when
		Collection<MetricValue> findValues = metricService.findValues(metric,
				new Date(), null);

		// then
		assertThat(findValues).hasSize(1);

	}

	@Test
	public void shouldGetValueFromTimeToTime() {
		// given
		Long metric = metricService.save(1L, "new Metric");
		metricService.addValue(metric, 1.0);
		Long addValue = metricService.addValue(metric, 5.0);
		MetricValue metricValue = metricValueRepository.findOne(addValue);
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DAY_OF_MONTH, -2);

		Calendar to = Calendar.getInstance();
		to.add(Calendar.DAY_OF_MONTH, 2);

		metricValue.setDate(to.getTime());

		// when
		Collection<MetricValue> findValues = metricService.findValues(metric,
				from.getTime(), to.getTime());

		// then
		assertThat(findValues).hasSize(2);

	}

	@Test
	public void shouldGetValueFromTimeToTimeAndJoinThemWhenThereIsTooMuchPoints() {
		// given
		MetricService.MAX_PACK_SZIE = 10;

		Long metric = metricService.save(1L, "new Metric");

		for (int i = 0; i < MetricService.MAX_PACK_SZIE * 2; i++) {
			metricService.addValue(metric, Double.valueOf(i));
		}

		Calendar from = Calendar.getInstance();
		from.add(Calendar.DAY_OF_MONTH, -2);

		Calendar to = Calendar.getInstance();
		to.add(Calendar.DAY_OF_MONTH, 2);

		// when
		Collection<MetricValue> findValues = metricService.findValues(metric,
				from.getTime(), to.getTime());

		assertThat(findValues).hasSize(MetricService.MAX_PACK_SZIE);
	}
}
