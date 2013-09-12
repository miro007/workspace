package pl.com.stream.rdp.repo;

import java.util.List;

import pl.com.stream.rdp.model.Meeting;

public interface MeetingRepository extends BaseRepo<Meeting> {

	public Meeting findByNameContaining(String name);

	public List<Meeting> findByDevOrderByCreationDateAsc(Boolean value);

}
