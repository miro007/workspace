package pl.com.stream.metrics.rest;

import java.io.Serializable;
import java.util.Date;

public class MetricValueWO implements Serializable {
	private Date date;
	private Double value;

	public MetricValueWO(Date date, Double value) {
		this.date = date;
		this.value = value;
	}

	public MetricValueWO() {
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
