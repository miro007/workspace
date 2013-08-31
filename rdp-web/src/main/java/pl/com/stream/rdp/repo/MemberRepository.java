
package pl.com.stream.rdp.repo;

import org.springframework.data.mongodb.repository.Query;

import pl.com.stream.rdp.model.Member;

public interface MemberRepository extends BaseRepo<Member> {

	@Query
	Member findByEmail(String memberEmail);

}
