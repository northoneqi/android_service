package com.banana.admin.bean;

/**
 * <p>话题 Entity</p>
 * @author dell
 * @data 2016-09-14
 * @version 1.0
 */
public class Rules {
	
	private String name;
	private int topic_id;
	private int typeid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
}
