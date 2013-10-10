
package pl.com.stream.topfirma.partner;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserContext {

	private String login;
	private boolean authorized;

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
		this.authorized = true;
	}

	public boolean isAuthorized() {
		return authorized;
	}
}
