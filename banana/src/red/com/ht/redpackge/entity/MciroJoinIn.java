package com.ht.redpackge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_MciroJoinIn")
public class MciroJoinIn implements java.io.Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id
	 */
	private	int	LuckyId		;
	/**
	 * 活动编号
	 */
	private	int	ActivityId		;
	/**
	 * 微信用户Id
	 */
	private	String	OpenId		;
	/**
	 * 扫描产生的url
	 */
	private	String	Url		;
	/**
	 * 原URL
	 */
	private	String	Source		;
	/**
	 * 是否分享
	 */
	private	int	checklog		;
	
	@Id
	@Column(name="LuckyId")
	public int getLuckyId() {
		return LuckyId;
	}
	public void setLuckyId(int luckyId) {
		LuckyId = luckyId;
	}
	public int getActivityId() {
		return ActivityId;
	}
	public void setActivityId(int activityId) {
		ActivityId = activityId;
	}
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public int getChecklog() {
		return checklog;
	}
	public void setChecklog(int checklog) {
		this.checklog = checklog;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
