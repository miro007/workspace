
package pl.com.stream.rdp.service;

import java.io.Serializable;

public class MemberDto implements Serializable {

	private String firstName;
	private String lastName;
	private String email;

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
