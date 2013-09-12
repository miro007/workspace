package pl.com.stream.rdp.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;

import pl.com.stream.rdp.Context;

public abstract class RootDocument {

	@Field
	private Long classVersion = 1L;
	@Version
	private Long objectVersion;
	@Field
	private Date creationDate;

	public Long getClassVersion() {
		return classVersion;
	}

	public void setClassVersion(final Long classVersion) {
		this.classVersion = classVersion;
	}

	public Long getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(final Long objectVersion) {
		this.objectVersion = objectVersion;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Transient
	MongoTemplate mongoTemplate = Context.getService(MongoTemplate.class);

}
