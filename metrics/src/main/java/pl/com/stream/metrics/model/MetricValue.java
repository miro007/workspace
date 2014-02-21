package pl.com.stream.metrics.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MetricValue extends BaseEntity {
	@JsonIgnore
	@ManyToOne
	private Metric metric;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private Integer year;
	private Integer month;
	private Integer day;
	private Integer hour;
	private Integer minute;

	private Double value;

	public MetricValue() {
	}

	@Override
	public String toString() {
		return "Date " + date + " val " + value;
	}

	public MetricValue(Date date, Double value) {
		this.date = date;
		this.value = value;
	}

	public Metric getMetric() {
		return metric;
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		this.year = instance.get(Calendar.YEAR);
		this.month = instance.get(Calendar.MONTH);
		this.day = instance.get(Calendar.DAY_OF_MONTH);
		this.hour = instance.get(Calendar.HOUR_OF_DAY);
		this.minute = instance.get(Calendar.MINUTE);
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

}
