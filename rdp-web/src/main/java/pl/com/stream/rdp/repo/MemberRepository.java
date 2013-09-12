package pl.com.stream.rdp.repo;

import pl.com.stream.rdp.model.Member;

public interface MemberRepository extends BaseRepo<Member> {

	Member findByEmail(String memberEmail);

}
