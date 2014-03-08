package pl.com.stream.metrics.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DashboardEvent extends BaseEntity {
	@JsonIgnore
	@ManyToOne
	private Dashboard dashboard;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String name;

	public DashboardEvent() {
	}

	public DashboardEvent(Dashboard dashboard, String event) {
		this.setDashboard(dashboard);
		name = event;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

}
