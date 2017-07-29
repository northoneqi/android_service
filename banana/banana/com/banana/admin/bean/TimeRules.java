package com.banana.admin.bean;

import java.util.List;

/**
 * <p>主题时间 Entity</p>
 * @author dell
 * @data 2016-09-14
 * @version 1.0
 */
public class TimeRules {
	
	private List<Rules> rulesList;
	
	private String dateStart;
	
	private String dateEnd;

	public List<Rules> getRulesList() {
		return rulesList;
	}

	public void setRulesList(List<Rules> rulesList) {
		this.rulesList = rulesList;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	
	
	
}
