package pl.com.stream.metrics.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MetricEvent extends BaseEntity {
	@JsonIgnore
	@ManyToOne
	private Metric metric;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String name;

	public MetricEvent() {
	}

	public MetricEvent(Metric metric, String event) {
		this.metric = metric;
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

	public void setMetric(Metric metric) {
		this.metric = metric;

	}

}
