import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.com.stream.rdp.model.Comment;
import pl.com.stream.rdp.model.Meeting;
import pl.com.stream.rdp.model.Member;
import pl.com.stream.rdp.repo.CommentRepository;
import pl.com.stream.rdp.repo.MeetingRepository;
import pl.com.stream.rdp.repo.MemberRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class MeetingTest {

	@Inject
	MeetingRepository repository;
	@Inject
	CommentRepository commentRepository;
	@Inject
	MemberRepository memberRepository;

	@Test
	public void shouldCreateSimpleMeeting() throws Exception {
		// given
		Meeting meeting = new Meeting();
		meeting.setName("Scala");
		meeting.setDescription("Learning of Scala");

		// when
		repository.save(meeting);

		// then
		assertThat(repository.count()).isGreaterThan(0);
	}

	@Test
	public void shouldUpdateMeeting() throws Exception {
		// given
		Meeting meeting = new Meeting();
		meeting.setName("Scala");
		meeting.setDescription("Learning of Scala");
		repository.save(meeting);

		// when
		Meeting one = repository.findOne(meeting.getId());
		one.setName("Scala Pro");
		repository.save(one);

		// then
		assertThat(repository.findOne(meeting.getId()).getName()).isEqualTo("Scala Pro");
	}

	@Test
	public void shouldFindByName() throws Exception {
		// given
		Meeting meeting = new Meeting();
		meeting.setName("Scala Pro");
		repository.save(meeting);

		// when
		Meeting one = repository.findByNameContaining("Scala");

		// then
		assertThat(one).isNotNull();
	}

	@Test
	public void shouldDeleteMeeting() throws Exception {
		// given
		long count = repository.count();
		Meeting meeting = new Meeting();
		meeting.setName("Scala");
		meeting.setDescription("Learning of Scala");
		repository.save(meeting);

		// when
		repository.delete(meeting.getId());

		// then
		assertThat(repository.count()).isEqualTo(count);
	}

	@Test
	public void shouldAddMember() throws Exception {
		// given
		Meeting meeting = new Meeting();
		meeting.setName("Scala");
		meeting.setDescription("Learning of Scala");
		repository.save(meeting);

		// when
		Meeting one = repository.findOne(meeting.getId());
		Member findByEmail = findMember("email");
		one.addMember(findByEmail);
		repository.save(one);

		// then
		assertThat(repository.findOne(meeting.getId()).getMembers().size()).isEqualTo(1);
	}

	private Member findMember(final String email) {
		Member findByEmail = memberRepository.findByEmail(email);
		if (findByEmail == null) {
			memberRepository.save(new Member(email));
		}
		return memberRepository.findByEmail(email);
	}

	@Test
	public void shouldAddMemberAndAutomaticlyObserwer() throws Exception {
		// given
		Meeting meeting = new Meeting();
		meeting.setName("Scala");
		meeting.setDescription("Learning of Scala");
		repository.save(meeting);

		// when
		Meeting one = repository.findOne(meeting.getId());
		Member member = one.addMember(findMember("email"));
		repository.save(one);

		// then
		assertThat(repository.findOne(meeting.getId()).getObservers()).containsExactly(member);
	}

	@Test
	public void shouldCommentTask() throws Exception {
		// given
		Meeting meeting = new Meeting();
		meeting.setName("Scala");
		meeting.setDescription("Learning of Scala");
		repository.save(meeting);

		// when
		Comment comment = new Comment("Jestem za", memberRepository.findByEmail("miro007@tlen.pl"));
		commentRepository.save(comment);
		meeting.addComment(comment);
		repository.save(meeting);

		// then
		assertThat(repository.findOne(meeting.getId()).getComments()).containsExactly(comment);

	}

	@Test
	public void shouldUpdateCommentTask() throws Exception {
		// given
		Meeting meeting = new Meeting();
		meeting.setName("Scala");
		meeting.setDescription("Learning of Scala");

		Comment comment = new Comment("Jestem za", memberRepository.findByEmail("miro007@tlen.pl"));
		commentRepository.save(comment);
		meeting.addComment(comment);;
		repository.save(meeting);

		// when
		comment = commentRepository.findOne(comment.getId());
		comment.setContent("New Comment");
		commentRepository.save(comment);

		// then
		assertThat(repository.findOne(meeting.getId()).getComments()).containsExactly(comment);
	}

	@After
	public void clear() {
		// repository.deleteAll();
	}
}
