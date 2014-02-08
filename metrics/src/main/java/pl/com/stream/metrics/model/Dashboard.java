package pl.com.stream.metrics.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dashboard extends BaseEntity {

	private String name;
	@ManyToOne
	@JsonIgnore
	private Account account;
	@JsonIgnore
	@OneToMany(mappedBy = "dashboard", cascade = CascadeType.ALL)
	private Set<Metric> metricSet = new HashSet<Metric>();

	public Dashboard() {
	}

	public Dashboard(String name) {
		this.name = name;
	}

	public Dashboard(String name, Account account) {
		this.name = name;
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<Metric> getMetricSet() {
		return metricSet;
	}

	public void addMetric(Metric metric) {
		metric.setDashboard(this);
		save(metric);
		metricSet.add(metric);
	}

}
