package pl.com.stream.metrics.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Metric extends BaseEntity {

	private String name;

	private String pullLink;
	@JsonIgnore
	@ManyToOne(optional = true)
	private Dashboard dashboard;

	public Metric() {
	}

	public Metric(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public String getPullLink() {
		return pullLink;
	}

	public void setPullLink(String pullLink) {
		this.pullLink = pullLink;
	}

}
