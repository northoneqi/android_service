package com.ht.zc.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ht.sys.util.DateUtil;

/**
 * 类功能说明: BackinfoTwo
 * <p>众筹活动计划表</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:04
 */
@Entity
@Table(name="t_crowdfunding_backInfo_two")
public class BackinfoTwo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum ActivityStatus{
		/**未开始*/
		unstart,
		/**进行中*/
		underway,
		/**暂停*/
		pause,
		/**结束*/
		end
	}
	/** 属性 */

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	
	/** 活动id */
	private java.lang.Long id;
	
	/** 活动期数 */
	private java.lang.String activityNum;
	
	/** 活动状态：未开始(0)、进行中(1)、暂停(2)、结束(3) */
	private java.lang.Long activityStatus;
	
	/** 活动介绍 */
	private java.lang.String activityInfo;
	
	/** 众筹人份数:几个人众筹 */
	private java.lang.String playNum;
	
	/** 未完成是否可以取消：0-不可取消，1-可取消 */
	private java.lang.Boolean isCanal;
	
	/** 是否可以自己支付：0-不可支付，1-可以支付 */
	private java.lang.Boolean isPayall;
	
	/** 最少允许支付剩余金额的份数：例：2，表示份数还剩2份或小于2份时，发起人可以支付 */
	private java.lang.Long minPayNumber;
	
	/** 最多发起次数：0到n次 */
	private java.lang.Long maxPlayMore;
	
	/** 最多支付次数：0到n次 */
	private java.lang.Long maxPayMore;
	
	/** 活动开始时间 */
	private java.lang.Long activityBeginTime;
	
	/** 活动结束时间 */
	private java.lang.Long activityEndTime;
	
	/** 活动计划经办人 */
	private java.lang.String activityAuto;
	
	/** 活动计划时间 */
	private java.lang.Long activityAddTime;
	
	/** 备注 */
	private java.lang.String remark;
	
	/** 每天配送上限 */
	private java.lang.Long deliveryUpLimit;
	
	/** 每天发起上限*/
	private java.lang.Long launchUpLimit;
	
	
	
	/** setter\getter方法 */
	

	/**
	 * 获取活动id
	 * 
	 * @return 活动id
	 */
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACTIVITY_ID")
	public java.lang.Long getId() {
		return this.id;
	}
	
	/**
	 * 设置活动id
	 * 
	 * @param activityId
	 *            活动id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	
	/**
	 * 获取活动期数
	 * 
	 * @return 活动期数
	 */
	@Column(name="ACTIVITY_NUM")
	public java.lang.String getActivityNum() {
		return this.activityNum;
	}
	
	/**
	 * 设置活动期数
	 * 
	 * @param activityNum
	 *            活动期数
	 */
	public void setActivityNum(java.lang.String activityNum) {
		this.activityNum = activityNum;
	}
	
	/**
	 * 获取活动状态：未开始(0)、进行中(1)、暂停(2)、结束(3)
	 * 
	 * @return 活动状态：未开始(0)、进行中(1)、暂停(2)、结束(3)
	 */
	@Column(name="ACTIVITY_STATUS")
	public java.lang.Long getActivityStatus() {
		return this.activityStatus;
	}
	
	/**
	 * 设置活动状态：未开始(0)、进行中(1)、暂停(2)、结束(3)
	 * 
	 * @param activityStatus
	 *            活动状态：未开始(0)、进行中(1)、暂停(2)、结束(3)
	 */
	public void setActivityStatus(java.lang.Long activityStatus) {
		this.activityStatus = activityStatus;
	}
	
	/**
	 * 获取活动介绍
	 * 
	 * @return 活动介绍
	 */
	@Column(name="ACTIVITY_INFO")
	public java.lang.String getActivityInfo() {
		return this.activityInfo;
	}
	
	/**
	 * 设置活动介绍
	 * 
	 * @param activityInfo
	 *            活动介绍
	 */
	public void setActivityInfo(java.lang.String activityInfo) {
		this.activityInfo = activityInfo;
	}
	
	/**
	 * 获取众筹人份数:几个人众筹
	 * 
	 * @return 众筹人份数:几个人众筹
	 */
	@Column(name="PLAY_NUM")
	public java.lang.String getPlayNum() {
		return this.playNum;
	}
	
	/**
	 * 设置众筹人份数:几个人众筹
	 * 
	 * @param playNum
	 *            众筹人份数:几个人众筹
	 */
	public void setPlayNum(java.lang.String playNum) {
		this.playNum = playNum;
	}
	
	/**
	 * 获取未完成是否可以取消：0-不可取消，1-可取消
	 * 
	 * @return 未完成是否可以取消：0-不可取消，1-可取消
	 */
	@Column(name="IS_CANAL")
	public java.lang.Boolean getIsCanal() {
		return this.isCanal;
	}
	
	/**
	 * 设置未完成是否可以取消：0-不可取消，1-可取消
	 * 
	 * @param isCanal
	 *            未完成是否可以取消：0-不可取消，1-可取消
	 */
	public void setIsCanal(java.lang.Boolean isCanal) {
		this.isCanal = isCanal;
	}
	
	/**
	 * 获取是否可以自己支付：0-不可支付，1-可以支付
	 * 
	 * @return 是否可以自己支付：0-不可支付，1-可以支付
	 */
	@Column(name="IS_PAYALL")
	public java.lang.Boolean getIsPayall() {
		return this.isPayall;
	}
	
	/**
	 * 设置是否可以自己支付：0-不可支付，1-可以支付
	 * 
	 * @param isPayall
	 *            是否可以自己支付：0-不可支付，1-可以支付
	 */
	public void setIsPayall(java.lang.Boolean isPayall) {
		this.isPayall = isPayall;
	}
	
	/**
	 * 获取最少允许支付剩余金额的份数：例：2，表示份数还剩2份或小于2份时，发起人可以支付
	 * 
	 * @return 最少允许支付剩余金额的份数：例：2，表示份数还剩2份或小于2份时，发起人可以支付
	 */
	@Column(name="MIN_PAY_NUMBER")
	public java.lang.Long getMinPayNumber() {
		return this.minPayNumber;
	}
	
	/**
	 * 设置最少允许支付剩余金额的份数：例：2，表示份数还剩2份或小于2份时，发起人可以支付
	 * 
	 * @param minPayNumber
	 *            最少允许支付剩余金额的份数：例：2，表示份数还剩2份或小于2份时，发起人可以支付
	 */
	public void setMinPayNumber(java.lang.Long minPayNumber) {
		this.minPayNumber = minPayNumber;
	}
	
	/**
	 * 获取最多发起次数：0到n次
	 * 
	 * @return 最多发起次数：0到n次
	 */
	@Column(name="MAX_PLAY_MORE")
	public java.lang.Long getMaxPlayMore() {
		return this.maxPlayMore;
	}
	
	/**
	 * 设置最多发起次数：0到n次
	 * 
	 * @param maxPlayMore
	 *            最多发起次数：0到n次
	 */
	public void setMaxPlayMore(java.lang.Long maxPlayMore) {
		this.maxPlayMore = maxPlayMore;
	}
	
	/**
	 * 获取最多支付次数：0到n次
	 * 
	 * @return 最多支付次数：0到n次
	 */
	@Column(name="MAX_PAY_MORE")
	public java.lang.Long getMaxPayMore() {
		return this.maxPayMore;
	}
	
	/**
	 * 设置最多支付次数：0到n次
	 * 
	 * @param maxPayMore
	 *            最多支付次数：0到n次
	 */
	public void setMaxPayMore(java.lang.Long maxPayMore) {
		this.maxPayMore = maxPayMore;
	}
	
	/**
	 * 获取活动开始时间
	 * 
	 * @return 活动开始时间
	 */
	@Column(name="ACTIVITY_BEGIN_TIME")
	public java.lang.Long getActivityBeginTime() {
		return this.activityBeginTime;
	}
	
	/**
	 * 设置活动开始时间
	 * 
	 * @param activityBeginTime
	 *            活动开始时间
	 */
	public void setActivityBeginTime(java.lang.Long activityBeginTime) {
		this.activityBeginTime = activityBeginTime;
	}
	
	/**
	 * 获取活动结束时间
	 * 
	 * @return 活动结束时间
	 */
	@Column(name="ACTIVITY_END_TIME")
	public java.lang.Long getActivityEndTime() {
		return this.activityEndTime;
	}
	
	/**
	 * 设置活动结束时间
	 * 
	 * @param activityEndTime
	 *            活动结束时间
	 */
	public void setActivityEndTime(java.lang.Long activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	
	/**
	 * 获取活动计划经办人
	 * 
	 * @return 活动计划经办人
	 */
	@Column(name="ACTIVITY_AUTO")
	public java.lang.String getActivityAuto() {
		return this.activityAuto;
	}
	
	/**
	 * 设置活动计划经办人
	 * 
	 * @param activityAuto
	 *            活动计划经办人
	 */
	public void setActivityAuto(java.lang.String activityAuto) {
		this.activityAuto = activityAuto;
	}
	
	/**
	 * 获取活动计划时间
	 * 
	 * @return 活动计划时间
	 */
	@Column(name="ACTIVITY_ADD_TIME")
	public java.lang.Long getActivityAddTime() {
		return this.activityAddTime;
	}
	
	/**
	 * 设置活动计划时间
	 * 
	 * @param activityAddTime
	 *            活动计划时间
	 */
	public void setActivityAddTime(java.lang.Long activityAddTime) {
		this.activityAddTime = activityAddTime;
	}
	
	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	@Column(name="REMARK")
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置备注
	 * 
	 * @param remark
	 *            备注
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	/**
	 *  每天配送上限
	 * 
	 * @param delivery_up_limit
	 *            
	 */
	@Column(name="delivery_up_limit")
	public java.lang.Long getDeliveryUpLimit() {
		return this.deliveryUpLimit;
	}
	/**
	 *  每天配送上限
	 * 
	 * @param deliveryUpLimit
	 *            
	 */
	public void setDeliveryUpLimit(java.lang.Long deliveryUpLimit) {
		this.deliveryUpLimit = deliveryUpLimit;
	}

	/**
	 * 每天发起上限
	 * 
	 * @param launchUpLimit
	 *            
	 */
	@Column(name="launch_up_limit")
	public java.lang.Long getLaunchUpLimit() {
		return this.launchUpLimit;
	}
	/**
	 *  每天发起上限
	 * 
	 * @param launch_up_limit
	 *             
	 */
	public void setLaunchUpLimit(java.lang.Long launchUpLimit) {
		this.launchUpLimit = launchUpLimit;
	}
	

	@Transient
	public String getActivityStatusStr(){
		if(this.getActivityStatus() == 0) {
			return "未开始";
		}else if(this.getActivityStatus() == 1){
			return "进行中";
		}else if(this.getActivityStatus() == 2) {
			return "暂停";
		}else {
			return "结束";
		}
	}
	
	@Transient
	public String getIsCanalStr(){
		if(this.getIsCanal()) {
			return "是";
		}else {
			return "否";
		}
	}

	@Transient
	public String getIsPayallStr(){
		if(this.getIsPayall()){
			return "是";
		}else{
			return "否";
		}
	}
	
	@Transient
	public String getActivityBeginTimeStr(){
		return DateUtil.timesampToStr(this.getActivityBeginTime(), "yyyy-MM-dd HH:mm:ss");
	}
	
	@Transient
	public String getActivityEndTimeStr(){
		return DateUtil.timesampToStr(this.getActivityEndTime(), "yyyy-MM-dd HH:mm:ss");
	}
	
	@Transient
	public String getActivityAddTimeStr(){
		return DateUtil.timesampToStr(this.getActivityAddTime(), "yyyy-MM-dd HH:mm:ss");
	}
	
}