package pl.com.stream.rdp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pl.com.stream.rdp.model.Meeting;
import pl.com.stream.rdp.model.Member;
import pl.com.stream.rdp.repo.MeetingRepository;
import pl.com.stream.rdp.repo.MemberRepository;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class MeetingService {

	@Inject
	MeetingRepository repo;
	@Inject
	MemberService memberService;
	@Inject
	MemberRepository memberRepository;

	public void save(final MeetingDto dto) {
		Meeting meeting = new Meeting();
		if (dto.getId() != null) {
			meeting = repo.findOne(dto.getId());
		}
		BeanUtils.copyProperties(dto, meeting);
		repo.save(meeting);
	}

	public void addMember(String id, String email) {
		Meeting meeting = repo.findOne(id);
		Member findByEmail = memberRepository.findByEmail(email);
		if (!meeting.getMembers().contains(findByEmail)) {
			meeting.getMembers().add(findByEmail);
		}
		repo.save(meeting);

	}

	public void removeMember(String id, String email) {
		Meeting meeting = repo.findOne(id);
		for (Iterator iterator = meeting.getMembers().iterator(); iterator
				.hasNext();) {
			Member type = (Member) iterator.next();
			if (email.equals(type.getEmail())) {
				iterator.remove();
			}

		}
		repo.save(meeting);
	}

	public MeetingDto find(String id) {
		MeetingDto dto = new MeetingDto();
		Meeting meeting = repo.findOne(id);
		BeanUtils.copyProperties(meeting, dto);
		return dto;
	}

	public List<MeetingRV> findAll() {
		Iterable<Meeting> findAll = repo.findAll();
		List<MeetingRV> resutl = new ArrayList<MeetingRV>();
		for (Meeting meeting : findAll) {
			MeetingRV meetingRV = new MeetingRV();
			BeanUtils.copyProperties(meeting, meetingRV);
			meetingRV.setMembersNames(Lists.transform(meeting.getMembers(),
					new Function<Member, String>() {

						@Override
						public String apply(@Nullable Member input) {
							return input.getFirstName() + " "
									+ input.getLastName();
						}
					}));
			resutl.add(meetingRV);
		}
		return resutl;

	}
}
