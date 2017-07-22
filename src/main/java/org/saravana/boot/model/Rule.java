package org.saravana.boot.model;

public class Rule {

	private Integer id;

	private String name;

	private String packageName;

	private String author;

	private Helper journalHelper;

	private Helper controlHelper;

	private MatchCriteria criteria;

	private Data journalData;

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

	public Helper getControlHelper() {
		return controlHelper;
	}

	public Helper getJournalHelper() {
		return journalHelper;
	}

	public void setControlHelper(Helper controlHelper) {
		this.controlHelper = controlHelper;
	}

	public void setJournalHelper(Helper journalHelper) {
		this.journalHelper = journalHelper;
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
