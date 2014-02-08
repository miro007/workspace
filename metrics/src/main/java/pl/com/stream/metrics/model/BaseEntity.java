package pl.com.stream.metrics.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import pl.com.stream.metrics.lib.Context;
import pl.com.stream.metrics.repo.CommonRepository;

@MappedSuperclass
public class BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public void save(BaseEntity entity) {
		Context.getService(CommonRepository.class).save(entity);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
