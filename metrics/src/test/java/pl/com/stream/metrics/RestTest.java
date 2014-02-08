package pl.com.stream.metrics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import pl.com.stream.metrics.config.Application;
import pl.com.stream.metrics.model.Account;
import pl.com.stream.metrics.repo.AccountRepository;
import pl.com.stream.metrics.rest.DemoController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class RestTest {
	@Autowired
	private AccountRepository userRepository;

	private MockMvc restUserMockMvc;

	@Before
	public void setup() {
		DemoController userResource = new DemoController();
		userResource.repo = userRepository;
		// ReflectionTestUtils.setField(userResource, "userRepository",
		// userRepository);
		this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource)
				.build();
	}

	@Test
	public void testGetExistingUser() throws Exception {
		userRepository.save(new Account("Administrator", "sad"));
		// restUserMockMvc
		// .perform(
		// get("/app/rest/users/1").accept(
		// MediaType.APPLICATION_JSON))
		// .andExpect(status().isOk())
		// .andDo(MockMvcResultHandlers.print())
		// .andExpect(content().contentType("application/json"))
		// .andExpect(jsonPath("$.firstName").value("Administrator"));
	}
}
