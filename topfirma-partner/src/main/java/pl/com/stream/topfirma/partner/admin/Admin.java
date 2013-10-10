
package pl.com.stream.topfirma.partner.admin;

import javax.validation.constraints.NotNull;

public class Admin {

	@NotNull
	private String email;
	@NotNull
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}
