package com.ht.hb.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 类功能说明: Mciroprize
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-19 14:08:01
 */

@Entity
@Table(name="t_mciroprize")
public class Mciroprize implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	/** 属性 */

	/** 奖品Id */
	private java.lang.Integer prizeId;
	
	/** 活动Id */
	private java.lang.Integer activityId;
	
	/** 奖品金额 */
	private java.lang.String prizeContent;
	
	/** 数量 */
	private java.lang.Integer prizeNumber;
	
	/** 红包卡号 */
	private java.lang.String cardNo;
	
	/** 红包使用状态:0-未使用,1-已使用 */
	private java.lang.String cardState;
	
	/** 奖品类型:根据奖品金额从小到大的依次为1-n,例5元对应1,8元对应2 */
	private java.lang.Integer prizeType;
	
	/** 开始时间 */
	private java.util.Date stardtime;
	
	/** 结束时间 */
	private java.util.Date stoptime;
	
	/** 抽奖状态：0--未抽，1-已抽 */
	private java.lang.String note;
	
	
	/** setter\getter方法 */
	
	/**
	 * 获取奖品Id
	 * 
	 * @return 奖品Id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PrizeId")
	public java.lang.Integer getPrizeId() {
		return this.prizeId;
	}
	
	/**
	 * 设置奖品Id
	 * 
	 * @param prizeId
	 *            奖品Id
	 */
	public void setPrizeId(java.lang.Integer prizeId) {
		this.prizeId = prizeId;
	}
	
	/**
	 * 获取活动Id
	 * 
	 * @return 活动Id
	 */
	@Column(name="ActivityId")
	public java.lang.Integer getActivityId() {
		return this.activityId;
	}
	
	/**
	 * 设置活动Id
	 * 
	 * @param activityId
	 *            活动Id
	 */
	public void setActivityId(java.lang.Integer activityId) {
		this.activityId = activityId;
	}
	
	/**
	 * 获取奖品金额
	 * 
	 * @return 奖品金额
	 */
	@Column(name="PrizeContent")
	public java.lang.String getPrizeContent() {
		return this.prizeContent;
	}
	
	/**
	 * 设置奖品金额
	 * 
	 * @param prizeContent
	 *            奖品金额
	 */
	public void setPrizeContent(java.lang.String prizeContent) {
		this.prizeContent = prizeContent;
	}
	
	/**
	 * 获取数量
	 * 
	 * @return 数量
	 */
	@Column(name="PrizeNumber")
	public java.lang.Integer getPrizeNumber() {
		return this.prizeNumber;
	}
	
	/**
	 * 设置数量
	 * 
	 * @param prizeNumber
	 *            数量
	 */
	public void setPrizeNumber(java.lang.Integer prizeNumber) {
		this.prizeNumber = prizeNumber;
	}
	
	/**
	 * 获取红包卡号
	 * 
	 * @return 红包卡号
	 */
	@Column(name="CardNo")
	public java.lang.String getCardNo() {
		return this.cardNo;
	}
	
	/**
	 * 设置红包卡号
	 * 
	 * @param cardNo
	 *            红包卡号
	 */
	public void setCardNo(java.lang.String cardNo) {
		this.cardNo = cardNo;
	}
	
	/**
	 * 获取红包使用状态:0-未使用,1-已使用
	 * 
	 * @return 红包使用状态:0-未使用,1-已使用
	 */
	@Column(name="CardState")
	public java.lang.String getCardState() {
		return this.cardState;
	}
	
	/**
	 * 设置红包使用状态:0-未使用,1-已使用
	 * 
	 * @param cardState
	 *            红包使用状态:0-未使用,1-已使用
	 */
	public void setCardState(java.lang.String cardState) {
		this.cardState = cardState;
	}
	
	/**
	 * 获取奖品类型:根据奖品金额从小到大的依次为1-n,例5元对应1,8元对应2
	 * 
	 * @return 奖品类型:根据奖品金额从小到大的依次为1-n,例5元对应1,8元对应2
	 */
	@Column(name="prizeType")
	public java.lang.Integer getPrizeType() {
		return this.prizeType;
	}
	
	/**
	 * 设置奖品类型:根据奖品金额从小到大的依次为1-n,例5元对应1,8元对应2
	 * 
	 * @param prizeType
	 *            奖品类型:根据奖品金额从小到大的依次为1-n,例5元对应1,8元对应2
	 */
	public void setPrizeType(java.lang.Integer prizeType) {
		this.prizeType = prizeType;
	}
	
	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */
	@Column(name="stardtime")
	public java.util.Date getStardtime() {
		return this.stardtime;
	}
	
	/**
	 * 设置开始时间
	 * 
	 * @param stardtime
	 *            开始时间
	 */
	public void setStardtime(java.util.Date stardtime) {
		this.stardtime = stardtime;
	}
	
	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */
	@Column(name="stoptime")
	public java.util.Date getStoptime() {
		return this.stoptime;
	}
	
	/**
	 * 设置结束时间
	 * 
	 * @param stoptime
	 *            结束时间
	 */
	public void setStoptime(java.util.Date stoptime) {
		this.stoptime = stoptime;
	}
	
	/**
	 * 获取抽奖状态：0--未抽，1-已抽
	 * 
	 * @return 抽奖状态：0--未抽，1-已抽
	 */
	@Column(name="note")
	public java.lang.String getNote() {
		return this.note;
	}
	
	/**
	 * 设置抽奖状态：0--未抽，1-已抽
	 * 
	 * @param note
	 *            抽奖状态：0--未抽，1-已抽
	 */
	public void setNote(java.lang.String note) {
		this.note = note;
	}
	

}