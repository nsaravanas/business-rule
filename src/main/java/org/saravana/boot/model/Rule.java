package org.saravana.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rule {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String name;

	@Column
	private String packageName;

	@Column
	private String author;

	@Column
	private String journalHelper;
	@Column
	private String controlHelper;

	@OneToOne
	private MatchCriteria criteria;

	@OneToOne
	private Data journalData;

	@OneToOne
	private Data controlData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getJournalHelper() {
		return journalHelper;
	}

	public void setJournalHelper(String journalHelper) {
		this.journalHelper = journalHelper;
	}

	public String getControlHelper() {
		return controlHelper;
	}

	public void setControlHelper(String controlHelper) {
		this.controlHelper = controlHelper;
	}

	public MatchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(MatchCriteria criteria) {
		this.criteria = criteria;
	}

	public Data getJournalData() {
		return journalData;
	}

	public void setJournalData(Data journalData) {
		this.journalData = journalData;
	}

	public Data getControlData() {
		return controlData;
	}

	public void setControlData(Data controlData) {
		this.controlData = controlData;
	}

}
