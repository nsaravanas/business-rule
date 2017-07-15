package org.saravana.boot.model;

import java.util.List;

public class MatchCriteria {

	private List<Field> fields;

	private String override;

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getOverride() {
		return override;
	}

	public void setOverride(String override) {
		this.override = override;
	}

}
