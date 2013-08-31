
package pl.com.stream.rdp.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Meeting extends IdRootDocument {

	private String name;
	private String description;
	private List<String> tags = new ArrayList<String>();

	@DBRef
	private final List<Member> members = new ArrayList<Member>();
	@DBRef
	private final List<Member> observers = new ArrayList<Member>();

	@DBRef
	private final List<Comment> comments = new ArrayList<Comment>();

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	public Member addMember(final Member member) {
		members.add(member);
		observers.add(member);
		return member;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void deleteMember(final Member member) {
		members.remove(member);

	}

	public void addObserver(final Member member) {
		getObservers().add(member);
	}

	public void deleteObserver(final Member member) {
		getObservers().remove(member);
	}

	public void addComment(final Comment comment) {
		getComments().add(comment);
	}

	public List<Member> getObservers() {
		return observers;
	}

	public List<Comment> getComments() {
		return comments;
	}

}
