package pl.com.stream.metrics.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MetricValuePackWO implements Serializable {
	private String name;
	private List<MetricValueWO> data = new ArrayList<MetricValueWO>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MetricValueWO> getData() {
		return data;
	}

	public void setData(List<MetricValueWO> data) {
		this.data = data;
	}
}
