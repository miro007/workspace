package pl.com.stream.metrics.repo;

import org.springframework.data.repository.CrudRepository;

import pl.com.stream.metrics.model.BaseEntity;

public interface CommonRepository extends CrudRepository<BaseEntity, Long> {
}
