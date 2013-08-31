
package pl.com.stream.rdp.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class Member extends IdRootDocument {

	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	private String email;

	public Member() {
	}

	public Member(final String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

}
