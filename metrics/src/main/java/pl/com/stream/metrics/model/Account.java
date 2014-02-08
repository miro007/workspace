package pl.com.stream.metrics.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Account extends BaseEntity {

	@Column(unique = true)
	private String email;
	private String password;
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<Dashboard> dashboardSet = new HashSet<Dashboard>();

	public Account() {
	}

	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Dashboard> getDashboardSet() {
		return dashboardSet;
	}

	public void addDashboard(Dashboard dashboard) {
		dashboard.setAccount(this);
		save(dashboard);
		this.dashboardSet.add(dashboard);
	}

}
