package com.ht.redpackge.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_MciroActivity")
public class MciroActivity implements  Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *主id
	 */
	private	int      ActivityId   ;  
	/**
	 * 活动名称
	 */
	private	String	 ActivityName ;
	/**
	 * 开始日期
	 */
	private	Date     BeginDate    ;
	/**
	 * 结束日期
	 */
	private Date	 StopDate     ;
	/**
	 * 奖品数量
	 */
	private	int	     PrizeCount   ;
	/**
	 * 开始时间
	 */
	private	Date     BeginTime    ;
	/**
	 * 结束时间
	 */
	private	Date      EndTime      ;
	/**
	 * 配送员配送量数量
	 */
	private	int		 PeiSongCnt   ;
	/**
	 * SKU 
	 */
	private	int		 SKU          ;
	/**
	 * 显示URL
	 */
	private	String	 ShowURL      ;
	
	@Id
	@Column(name="ActivityId")
	public int getActivityId() {
		return ActivityId;
	}
	public void setActivityId(int activityId) {
		ActivityId = activityId;
	}
	public String getActivityName() {
		return ActivityName;
	}
	public void setActivityName(String activityName) {
		ActivityName = activityName;
	}
	public Date getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(Date beginDate) {
		BeginDate = beginDate;
	}
	public Date getStopDate() {
		return StopDate;
	}
	public void setStopDate(Date stopDate) {
		StopDate = stopDate;
	}
	public int getPrizeCount() {
		return PrizeCount;
	}
	public void setPrizeCount(int prizeCount) {
		PrizeCount = prizeCount;
	}
	public Date getBeginTime() {
		return BeginTime;
	}
	public void setBeginTime(Date beginTime) {
		BeginTime = beginTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}
	public int getPeiSongCnt() {
		return PeiSongCnt;
	}
	public void setPeiSongCnt(int peiSongCnt) {
		PeiSongCnt = peiSongCnt;
	}
	public int getSKU() {
		return SKU;
	}
	public void setSKU(int sKU) {
		SKU = sKU;
	}
	public String getShowURL() {
		return ShowURL;
	}
	public void setShowURL(String showURL) {
		ShowURL = showURL;
	}
	
	
}
