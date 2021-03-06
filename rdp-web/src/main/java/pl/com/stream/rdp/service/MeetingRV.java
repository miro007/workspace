package pl.com.stream.rdp.service;

import java.io.Serializable;
import java.util.List;

public class MeetingRV implements Serializable {
	private String id;
	private String name;
	private String description;
	private List<String> tags;
	private List<String> membersNames;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null && !id.isEmpty())
			this.id = id;
	}

	public List<String> getMembersNames() {
		return membersNames;
	}

	public void setMembersNames(List<String> membersNames) {
		this.membersNames = membersNames;
	}
}
