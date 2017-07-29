package com.ht.redpackge.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_MciroLuckyPrize")
public class MciroLuckyPrize  implements java.io.Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private	int	ID		;
	/**
	 * 活动编号
	 */
	private	int	ActivityId		;
	/**
	 * 微信用户Id
	 */
	private	String	OpenId		;
	/**
	 * 奖品Id
	 */
	private	String	prizeId		;
	/**
	 * 是否抽奖
	 */
	private	int	isPrize		;
	/**
	 * 抽奖日期
	 */
	private	Date	PrizeDate		;
	
	@Id
	@Column(name="ID")	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}
	public int getIsPrize() {
		return isPrize;
	}
	public void setIsPrize(int isPrize) {
		this.isPrize = isPrize;
	}
	public Date getPrizeDate() {
		return PrizeDate;
	}
	public void setPrizeDate(Date prizeDate) {
		PrizeDate = prizeDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
}
