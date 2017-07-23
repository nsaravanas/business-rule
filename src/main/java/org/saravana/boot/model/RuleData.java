package org.saravana.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class RuleData {

	@Id
	private String name;

	@Column
	@Lob
	private String ruleData;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleData() {
		return ruleData;
	}

	public void setRuleData(String ruleData) {
		this.ruleData = ruleData;
	}

}
