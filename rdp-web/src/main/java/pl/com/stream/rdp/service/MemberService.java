package pl.com.stream.rdp.service;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pl.com.stream.rdp.model.Member;
import pl.com.stream.rdp.repo.MemberRepository;

@Service
public class MemberService {

	@Inject
	MemberRepository repo;

	public void save(final MemberDto dto) {
		Member member = new Member();
		BeanUtils.copyProperties(dto, member);
		repo.save(member);
	}

	public void login(MemberDto dto) {
		MemberDto result = new MemberDto();
		Member member = repo.findByEmail(dto.getEmail());
		if (member == null) {
			member = new Member();
			BeanUtils.copyProperties(dto, member);
			repo.save(member);
		}
	}
}
