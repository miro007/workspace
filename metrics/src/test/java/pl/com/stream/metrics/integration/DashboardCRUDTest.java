package pl.com.stream.metrics.integration;

import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.config.Application;
import pl.com.stream.metrics.repo.DashboardRepository;
import pl.com.stream.metrics.rest.DashboardResource;
import pl.com.stream.metrics.service.AccountService;
import pl.com.stream.metrics.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class DashboardCRUDTest {
	@Inject
	UserService userService;
	@Inject
	private DashboardRepository dashboardRepository;
	@Inject
	AccountService accountService;
	@Inject
	DashboardResource dashboardResource;

	private MockMvc restUserMockMvc;

	@Before
	public void setup() {
		this.restUserMockMvc = MockMvcBuilders.standaloneSetup(
				dashboardResource).build();
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
	public void shouldSaveDashboard() throws Exception {
		// given
		long count = dashboardRepository.count();

		String dashboardObject = "{\"name\":\"DashName\"}";

		// when
		restUserMockMvc
				.perform(
						post("/rest/dashboards").content(dashboardObject)
								.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

		// then
		long newCount = dashboardRepository.count();
		assertThat(count).isLessThan(newCount);

	}

	@Test
	public void shouldEditDashboard() throws Exception {
		// given
		String name = "dashName";
		String newName = "newName";
		Long dashboard = accountService.saveDashboard(
				userService.getIdAccount(), name);

		String dashboardObject = "{\"name\":\"" + newName + "\"}";

		// when
		assertThat(dashboardRepository.findByName(newName)).isNull();

		restUserMockMvc
				.perform(
						post("/rest/dashboards").content(dashboardObject)
								.contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

		// then

		assertThat(dashboardRepository.findByName(newName)).isNotNull();

	}

	@Test
	public void shouldDeleteDashboard() throws Exception {
		// given
		String name = "dashName";
		Long dashboard = accountService.saveDashboard(
				userService.getIdAccount(), name);

		// when
		assertThat(dashboardRepository.findByName(name)).isNotNull ();

		restUserMockMvc
				.perform(
						delete("/rest/dashboards/" + dashboard).contentType(
								MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());

		// then
		assertThat(dashboardRepository.findByName(name)).isNull();

	}
}
