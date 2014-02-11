package pl.com.stream.metrics.integration;

import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DecimalFormat;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.config.Application;
import pl.com.stream.metrics.repo.MetricRepository;
import pl.com.stream.metrics.rest.MetricResource;
import pl.com.stream.metrics.rest.MetricValueResource;
import pl.com.stream.metrics.service.AccountService;
import pl.com.stream.metrics.service.MetricService;
import pl.com.stream.metrics.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class MetricCRUDTest {
	@Inject
	UserService userService;
	@Inject
	private MetricRepository metricRepository;
	@Inject
	AccountService accountService;
	@Inject
	MetricService metricService;
	@Inject
	MetricResource metricResource;
	@Inject
	MetricValueResource metricValueResource;
	private MockMvc restUserMockMvc;

	@Before
	public void setup() {
		this.restUserMockMvc = MockMvcBuilders.standaloneSetup(metricResource,
				metricValueResource).build();
	}

	// HttpHeaders httpHeaders = new HttpHeaders();
	// URI uriOfUser = ServletUriComponentsBuilder
	// .fromCurrentContextPath()
	// .pathSegment("/dashboards/{id}")
	// .buildAndExpand(
	// Collections.singletonMap("id", saveDashboard))
	// .toUri();
	// httpHeaders.setLocation(uriOfUser);
	// return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);

	@Test
	public void shouldSave() throws Exception {
		// given
		long count = metricRepository.count();

		String metricObject = "{\"name\":\"MetricName\"}";

		// when
		restUserMockMvc
				.perform(
						post("/rest/dashboards/1/metrics")
								.content(metricObject).contentType(
										MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

		// then
		long newCount = metricRepository.count();
		assertThat(count).isLessThan(newCount);

		// then not mixed different dashboards
		restUserMockMvc
				.perform(
						get("/rest/dashboards/2/metrics").contentType(
								MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	@Test
	public void shouldEdit() throws Exception {
		// given
		String name = "dashName";
		String newName = "newName";
		metricService.save(1L, name, null);

		String metric = "{\"name\":\"" + newName + "\"}";

		// when
		assertThat(metricRepository.findByName(newName)).isNull();

		restUserMockMvc
				.perform(
						post("/rest/dashboards/1/metrics").content(metric)
								.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

		// then

		assertThat(metricRepository.findByName(newName)).isNotNull();

	}

	@Test
	public void shouldDelete() throws Exception {
		// given
		String name = "name";
		Long idMetric = metricService.save(1L, name, null);

		// when
		assertThat(metricRepository.findByName(name)).isNotNull();

		restUserMockMvc
				.perform(
						delete("/rest/dashboards/1/metrics/" + idMetric)
								.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

		// then
		assertThat(metricRepository.findByName(name)).isNull();

	}

	@Test
	public void shouldThrowErrorOnWrongCheckPullLink() throws Exception {
		// given
		String link = "wrong link";

		// when
		restUserMockMvc.perform(
				get("/rest/dashboards/1/metrics/checkPullLink?url=" + link)
						.contentType(MediaType.APPLICATION_JSON)).andExpect(
				status().isInternalServerError());

		// then

	}

	@Test
	public void shouldCheckValiePullLink() throws Exception {
		// given

		// when
		MvcResult returnDta = restUserMockMvc
				.perform(get("/rest/metricsValue/example"))
				.andExpect(status().isOk()).andReturn();

		// then
		String contentAsString = returnDta.getResponse().getContentAsString();
assertThat(Double.parseDouble(contentAsString)).isNotNull();
	}
}
