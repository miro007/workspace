package pl.com.stream.metrics.integration;

import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.config.Application;
import pl.com.stream.metrics.model.Dashboard;
import pl.com.stream.metrics.model.Metric;
import pl.com.stream.metrics.model.MetricValue;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.repo.MetricValueRepository;
import pl.com.stream.metrics.rest.MetricValueResource;
import pl.com.stream.metrics.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class MetricAddingValueTest {
	@Inject
	UserService userService;
	@Inject
	DashboardRepository dashboardRepository;
	@Inject
	private MetricRepository metricRepository;
	@Inject
	private MetricValueRepository metricValueRepository;
	@Inject
	MetricValueResource metricValueResource;
	private MockMvc restUserMockMvc;

	@Before
	public void setup() {
		this.restUserMockMvc = MockMvcBuilders.standaloneSetup(metricValueResource).build();
	}

	@Test
	public void shouldAddForSelectedMetric() throws Exception {
		// given
		Long idMetric = 1L;
		Double value = 1.23;

		// when
		List values = metricValueRepository.findByMetric(metricRepository.findOne(idMetric));

		restUserMockMvc.perform(get("/rest/metrics/values/add?idMetric=1&value=" + value))
				.andExpect(status().isOk());

		// then
		List newValues = metricValueRepository.findByMetric(metricRepository.findOne(idMetric));
		assertThat(values.size()).isLessThan(newValues.size());

	}

	@Test
	public void shouldAddByNameMetricValue() throws Exception {
		// given
		Long idMetric = 1L;
		Double value = 1.23;

		// when
		assertThat(dashboardRepository.findByAccountAndName(userService.getAccount(), "dash"))
				.isNull();

		restUserMockMvc.perform(
				get("/rest/metrics/values/addByName?dashboardName=dash&metricName=metric&value="
						+ value)).andExpect(status().isOk());

		// then
		Dashboard dashboard = dashboardRepository.findByAccountAndName(userService.getAccount(),
				"dash");
		assertThat(dashboard).isNotNull();

		Metric metric = dashboard.getMetricSet().iterator().next();

		List<MetricValue> values = metricValueRepository.findByMetric(metric);
		assertThat(values.get(0).getValue()).isEqualTo(value);

	}

}
