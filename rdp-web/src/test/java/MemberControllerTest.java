import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.StringContains;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.com.stream.rdp.repo.MemberRepository;
import pl.com.stream.rdp.service.MemberDto;
import pl.com.stream.rdp.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:web-app-context.xml")
public class MemberControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Inject
	MemberRepository memberRepository;
	@Inject
	MemberService memberService;

	ObjectMapper ob = new ObjectMapper();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldAddMember() throws Exception {
		// given
		String email = System.currentTimeMillis() + "@tlen.pl";
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail(email);
		memberDto.setFirstName("Mirek");
		memberDto.setLastName("Szajowski");

		// when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
				.post("/member/add").contentType(MediaType.APPLICATION_JSON)
				.content(ob.writeValueAsString(memberDto)));
		perform.andExpect(MockMvcResultMatchers.status().isOk());

		// then
		assertThat(memberRepository.findByEmail(email)).isNotNull();

	}

	// @Test
	// public void shouldLoginMember() throws Exception {
	// // given
	// String email = System.currentTimeMillis() + "@tlen.pl";
	// MemberDto memberDto = new MemberDto();
	// memberDto.setEmail(email);
	// memberDto.setFirstName("Mirek");
	// memberDto.setLastName("Szajowski");
	// memberService.save(memberDto);
	//
	// // when
	// ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post(
	// "/member/login?email=" + email).contentType(
	// MediaType.APPLICATION_JSON));
	// perform.andExpect(MockMvcResultMatchers.status().isOk());
	//
	// // then
	//
	// }

	public void shouldAdadMember() throws Exception {
		// when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get(
				"/member/521c9c9bd1f4b8c61242573d").contentType(
				MediaType.APPLICATION_JSON));
		;

		// then
		perform.andExpect(MockMvcResultMatchers.content().string(
				StringContains.containsString("email")));
	}

	public void shouldFindAll() throws Exception {
		// when
		ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get(
				"/member/all").contentType(MediaType.APPLICATION_JSON));
		;

		// then
		perform.andExpect(
				MockMvcResultMatchers.content().string(
						StringContains.containsString("email"))).andDo(
				MockMvcResultHandlers.print());
	}
}
