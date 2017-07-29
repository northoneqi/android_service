package com.ht.redpackge.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_MciroPrize")
public class MciroPrize implements java.io.Serializable{
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 奖品Id
	 */
	private	int	PrizeId		;
	
	/**
	 * 活动Id
	 */
	private	int	ActivityId		;
	/**
	 * 奖品金额
	 */
	private	String	PrizeContent		;
	/**
	 * 数量
	 */
	private	int	PrizeNumber		;
	/**
	 * 红包卡号
	 */
	private	int	Probably		;
	/**
	 * 抽奖状态
	 */
	private	String	note		;
	/**
	 * 红包卡号
	 */
	private	String	CardNo		;
	/**
	 * 红包使用状态
	 */
	private	int	CardState		;
	/**
	 * 奖品类型
	 */
	private	int	prizeType		;
	/**
	 * 开始时间
	 */
	private	Date	StartDate		;
	/**
	 * 结束时间
	 */
	private	Date	StopDate		;
	
	@Id
	@Column(name="PrizeId")
	public int getPrizeId() {
		return PrizeId;
	}
	public void setPrizeId(int prizeId) {
		PrizeId = prizeId;
	}
	public int getActivityId() {
		return ActivityId;
	}
	public void setActivityId(int activityId) {
		ActivityId = activityId;
	}
	public String getPrizeContent() {
		return PrizeContent;
	}
	public void setPrizeContent(String prizeContent) {
		PrizeContent = prizeContent;
	}
	public int getPrizeNumber() {
		return PrizeNumber;
	}
	public void setPrizeNumber(int prizeNumber) {
		PrizeNumber = prizeNumber;
	}
	public int getProbably() {
		return Probably;
	}
	public void setProbably(int probably) {
		Probably = probably;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public int getCardState() {
		return CardState;
	}
	public void setCardState(int cardState) {
		CardState = cardState;
	}
	public int getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(int prizeType) {
		this.prizeType = prizeType;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getStopDate() {
		return StopDate;
	}
	public void setStopDate(Date stopDate) {
		StopDate = stopDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
