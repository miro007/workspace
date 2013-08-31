
package pl.com.stream.rdp.service;

import java.io.Serializable;
import java.util.List;

public class MeetingDto implements Serializable {

	private String name;
	private String description;
	private List<String> tags;

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
}
