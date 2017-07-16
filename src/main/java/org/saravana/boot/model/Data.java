package org.saravana.boot.model;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private Integer id;
	private List<Field> fields = new ArrayList<>();

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
