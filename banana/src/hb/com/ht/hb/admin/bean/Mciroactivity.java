package com.ht.hb.admin.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 类功能说明: Mciroactivity 
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-19 9:56:47
 */

@Entity
@Table(name="t_mciroactivity")
public class Mciroactivity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	/** 属性 */

	/** activityId */
	private java.lang.Long activityId;
	
	/** 活动名称 */
	private java.lang.String activityName;
	
	/** 开始日期 */
	private Timestamp beginDate;
	
	/** 结束日期 */
	private Timestamp stopDate;
	
	/** 奖品数量 */
	private java.lang.Long prizeCount;
	
	/** 开始时间 */
	private String beginTime;
	
	/** 结束时间 */
	private String endTime;
	
	/** 配送员配送量数量 */
	private java.lang.Long peiSongCnt;
	
	/** SKU */
	private java.lang.Long sku;
	
	/** 显示URL */
	private java.lang.String showUrl;
	
	
	/** setter\getter方法 */
	
	/**
	 * 获取activityId
	 * 
	 * @return activityId
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ActivityId")
	public java.lang.Long getActivityId() {
		return this.activityId;
	}
	
	/**
	 * 设置activityId
	 * 
	 * @param activityId
	 *            activityId
	 */
	public void setActivityId(java.lang.Long activityId) {
		this.activityId = activityId;
	}
	
	/**
	 * 获取活动名称
	 * 
	 * @return 活动名称
	 */
	@Column(name="ActivityName")
	public java.lang.String getActivityName() {
		return this.activityName;
	}
	
	/**
	 * 设置活动名称
	 * 
	 * @param activityName
	 *            活动名称
	 */
	public void setActivityName(java.lang.String activityName) {
		this.activityName = activityName;
	}
	
	/**
	 * 获取开始日期
	 * 
	 * @return 开始日期
	 */
	@Column(name="BeginDate")
	public Timestamp getBeginDate() {
		return this.beginDate;
	}
	
	/**
	 * 设置开始日期
	 * 
	 * @param beginDate
	 *            开始日期
	 */
	public void setBeginDate(Timestamp beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * 获取结束日期
	 * 
	 * @return 结束日期
	 */
	@Column(name="StopDate")
	public Timestamp getStopDate() {
		return this.stopDate;
	}
	
	/**
	 * 设置结束日期
	 * 
	 * @param stopDate
	 *            结束日期
	 */
	public void setStopDate(Timestamp stopDate) {
		this.stopDate = stopDate;
	}
	
	/**
	 * 获取奖品数量
	 * 
	 * @return 奖品数量
	 */
	@Column(name="PrizeCount")
	public java.lang.Long getPrizeCount() {
		return this.prizeCount;
	}
	
	/**
	 * 设置奖品数量
	 * 
	 * @param prizeCount
	 *            奖品数量
	 */
	public void setPrizeCount(java.lang.Long prizeCount) {
		this.prizeCount = prizeCount;
	}
	
	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */
	@Column(name="BeginTime")
	public String getBeginTime() {
		return this.beginTime;
	}
	
	/**
	 * 设置开始时间
	 * 
	 * @param beginTime
	 *            开始时间
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */
	@Column(name="EndTime")
	public String getEndTime() {
		return this.endTime;
	}
	
	/**
	 * 设置结束时间
	 * 
	 * @param endTime
	 *            结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取配送员配送量数量
	 * 
	 * @return 配送员配送量数量
	 */
	@Column(name="PeiSongCnt")
	public java.lang.Long getPeiSongCnt() {
		return this.peiSongCnt;
	}
	
	/**
	 * 设置配送员配送量数量
	 * 
	 * @param peiSongCnt
	 *            配送员配送量数量
	 */
	public void setPeiSongCnt(java.lang.Long peiSongCnt) {
		this.peiSongCnt = peiSongCnt;
	}
	
	/**
	 * 获取SKU
	 * 
	 * @return SKU
	 */
	@Column(name="SKU")
	public java.lang.Long getSku() {
		return this.sku;
	}
	
	/**
	 * 设置SKU
	 * 
	 * @param sku
	 *            SKU
	 */
	public void setSku(java.lang.Long sku) {
		this.sku = sku;
	}
	
	/**
	 * 获取显示URL
	 * 
	 * @return 显示URL
	 */
	@Column(name="ShowURL")
	public java.lang.String getShowUrl() {
		return this.showUrl;
	}
	
	/**
	 * 设置显示URL
	 * 
	 * @param showUrl
	 *            显示URL
	 */
	public void setShowUrl(java.lang.String showUrl) {
		this.showUrl = showUrl;
	}
	

}