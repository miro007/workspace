
package pl.com.stream.rdp.repo;

import pl.com.stream.rdp.model.Meeting;

public interface MeetingRepository extends BaseRepo<Meeting> {

	public Meeting findByNameContaining(String name);
}
