
package pl.com.stream.rdp.service;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pl.com.stream.rdp.model.Meeting;
import pl.com.stream.rdp.repo.MeetingRepository;

@Service
public class MeetingService {

	@Inject
	MeetingRepository repo;

	public void save(final MeetingDto dto) {
		Meeting meeting = new Meeting();
		BeanUtils.copyProperties(dto, meeting);
		repo.save(meeting);
	}

}
