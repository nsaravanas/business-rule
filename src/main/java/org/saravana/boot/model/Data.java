package org.saravana.boot.model;

import java.util.List;

public class Data {

	private Integer id;
	private List<Field> data;

	public List<Field> getData() {
		return data;
	}

	public void setData(List<Field> data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
