
package pl.com.stream.rdp.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Comment extends IdRootDocument {

	private String content;
	@DBRef
	private Member owner;

	public Comment() {
	}

	public Comment(final String comment) {
		this.content = comment;
	}

	public Comment(final String comment, final Member owner) {
		content = comment;
		this.owner = owner;
	}

	public void setOwner(final Member member) {
		this.owner = member;
	}

	public void setContent(final String content) {
		this.content = content;

	}

	public String getContent() {
		return content;
	}

	public Member getOwner() {
		return owner;
	}

}
