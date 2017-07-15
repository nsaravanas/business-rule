package org.saravana.boot.model;

public class Rule {

	private String name;
	private String packageName;
	private String author;
	private String journalHelper;
	private String controlHelper;
	private MatchCriteria criteria;
	private Data journalData;
	private Data controlData;

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
