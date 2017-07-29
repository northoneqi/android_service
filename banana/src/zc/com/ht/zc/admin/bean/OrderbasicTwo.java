package com.ht.zc.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ht.sys.util.DateUtil;

/**
 * 类功能说明: OrderbasicTwo
 * <p>众筹订单基本信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-6 18:27:27
 */
@Entity
@Table(name="t_crowdfunding_orderbasic_two")
public class OrderbasicTwo implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	/** 属性 */

	/** 众筹订单编号 */
	private java.lang.String crowdfundingCode;
	
	/** 活动id */
	private java.lang.Long activityId;
	
	/** 是否在支付,0-未支付，1-已支付, */
	private java.lang.Long isPay;
	
	/** 众筹订单添加时间 */
	private java.lang.Long addTime;
	
	/** 众筹订单结束时间 */
	private java.lang.Long endTime;
	
	/** 众筹订单状态：0-进行中，1-已完成，2-已取消 */
	private java.lang.Long orderStatus;
	
	/** 众筹订单支付url */
	private java.lang.String payurl;
	
	/** 众筹订单发送日期 */
	private java.lang.Long sendDate;
	
	/** 众筹订单发送时间段 */
	private java.lang.Long sendTimes;
	
	/** 众筹订单支付时间 */
	private java.lang.Long paymenTtime;
	
	/** 众筹人份数：几人众筹 */
	private java.lang.Long playNum;
	
	/** 微信订单编号 */
	private java.lang.String wxOrdercode;
	
	/** 微信id */
	private java.lang.String openId;
	
	/** 发起人姓名 */
	private java.lang.String name;
	
	/** 发起人电话 */
	private java.lang.String tel;
	
	/** 省 */
	private java.lang.String province;
	
	/** 市 */
	private java.lang.String city;
	
	/** 区 */
	private java.lang.String area;
	
	/** 发起人住址 */
	private java.lang.String address;
	
	/** 商品唯一标识 */
	private java.lang.Long sku;
	
	/** 购买份数 */
	private java.lang.Long buyNumber;
	
	/** 商品名称 */
	private java.lang.String skuName;
	
	/** 商品单价 */
	private java.lang.Long goodPrice;
	
	/** 备注 */
	private java.lang.String remark;
	
	 
	
	
	
	/** setter\getter方法 */
	
	/**
	 * 获取众筹订单编号
	 * 
	 * @return 众筹订单编号
	 */
	@Id
	@Column(name="CROWDFUNDING_CODE")
	public java.lang.String getCrowdfundingCode() {
		return this.crowdfundingCode;
	}
	
	/**
	 * 设置众筹订单编号
	 * 
	 * @param crowdfundingCode
	 *            众筹订单编号
	 */
	public void setCrowdfundingCode(java.lang.String crowdfundingCode) {
		this.crowdfundingCode = crowdfundingCode;
	}
	
	/**
	 * 获取活动id
	 * 
	 * @return 活动id
	 */
	@Column(name="ACTIVITY_ID")
	public java.lang.Long getActivityId() {
		return this.activityId;
	}
	
	/**
	 * 设置活动id
	 * 
	 * @param activityId
	 *            活动id
	 */
	public void setActivityId(java.lang.Long activityId) {
		this.activityId = activityId;
	}
	
	/**
	 * 获取是否在支付,0-未支付，1-已支付,
	 * 
	 * @return 是否在支付,0-未支付，1-已支付,
	 */
	@Column(name="IS_PAY")
	public java.lang.Long getIsPay() {
		return this.isPay;
	}
	
	/**
	 * 设置是否在支付,0-未支付，1-已支付,
	 * 
	 * @param isPay
	 *            是否在支付,0-未支付，1-已支付,
	 */
	public void setIsPay(java.lang.Long isPay) {
		this.isPay = isPay;
	}
	
	/**
	 * 获取众筹订单添加时间
	 * 
	 * @return 众筹订单添加时间
	 */
	@Column(name="ADD_TIME")
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	/**
	 * 设置众筹订单添加时间
	 * 
	 * @param addTime
	 *            众筹订单添加时间
	 */
	public void setAddTime(java.lang.Long addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * 获取众筹订单结束时间
	 * 
	 * @return 众筹订单结束时间
	 */
	@Column(name="END_TIME")
	public java.lang.Long getEndTime() {
		return this.endTime;
	}
	
	/**
	 * 设置众筹订单结束时间
	 * 
	 * @param endTime
	 *            众筹订单结束时间
	 */
	public void setEndTime(java.lang.Long endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取众筹订单状态：0-进行中，1-已完成，2-已取消
	 * 
	 * @return 众筹订单状态：0-进行中，1-已完成，2-已取消
	 */
	@Column(name="ORDER_STATUS")
	public java.lang.Long getOrderStatus() {
		return this.orderStatus;
	}
	
	/**
	 * 设置众筹订单状态：0-进行中，1-已完成，2-已取消
	 * 
	 * @param orderStatus
	 *            众筹订单状态：0-进行中，1-已完成，2-已取消
	 */
	public void setOrderStatus(java.lang.Long orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 获取众筹订单支付url
	 * 
	 * @return 众筹订单支付url
	 */
	@Column(name="PAYURL")
	public java.lang.String getPayurl() {
		return this.payurl;
	}
	
	/**
	 * 设置众筹订单支付url
	 * 
	 * @param payurl
	 *            众筹订单支付url
	 */
	public void setPayurl(java.lang.String payurl) {
		this.payurl = payurl;
	}
	
	/**
	 * 获取众筹订单发送日期
	 * 
	 * @return 众筹订单发送日期
	 */
	@Column(name="SEND_DATE")
	public java.lang.Long getSendDate() {
		return this.sendDate;
	}
	
	/**
	 * 设置众筹订单发送日期
	 * 
	 * @param sendDate
	 *            众筹订单发送日期
	 */
	public void setSendDate(java.lang.Long sendDate) {
		this.sendDate = sendDate;
	}
	
	/**
	 * 获取众筹订单发送时间段
	 * 
	 * @return 众筹订单发送时间段
	 */
	@Column(name="SEND_TIMES")
	public java.lang.Long getSendTimes() {
		return this.sendTimes;
	}
	
	/**
	 * 设置众筹订单发送时间段
	 * 
	 * @param sendTimes
	 *            众筹订单发送时间段
	 */
	public void setSendTimes(java.lang.Long sendTimes) {
		this.sendTimes = sendTimes;
	}
	
	/**
	 * 获取众筹订单支付时间
	 * 
	 * @return 众筹订单支付时间
	 */
	@Column(name="PAYMEN_TTIME")
	public java.lang.Long getPaymenTtime() {
		return this.paymenTtime;
	}
	
	/**
	 * 设置众筹订单支付时间
	 * 
	 * @param paymenTtime
	 *            众筹订单支付时间
	 */
	public void setPaymenTtime(java.lang.Long paymenTtime) {
		this.paymenTtime = paymenTtime;
	}
	
	/**
	 * 获取众筹人份数：几人众筹
	 * 
	 * @return 众筹人份数：几人众筹
	 */
	@Column(name="PLAY_NUM")
	public java.lang.Long getPlayNum() {
		return this.playNum;
	}
	
	/**
	 * 设置众筹人份数：几人众筹
	 * 
	 * @param playNum
	 *            众筹人份数：几人众筹
	 */
	public void setPlayNum(java.lang.Long playNum) {
		this.playNum = playNum;
	}
	
	/**
	 * 获取微信订单编号
	 * 
	 * @return 微信订单编号
	 */
	@Column(name="WX_ORDERCODE")
	public java.lang.String getWxOrdercode() {
		return this.wxOrdercode;
	}
	
	/**
	 * 设置微信订单编号
	 * 
	 * @param wxOrdercode
	 *            微信订单编号
	 */
	public void setWxOrdercode(java.lang.String wxOrdercode) {
		this.wxOrdercode = wxOrdercode;
	}
	
	/**
	 * 获取微信id
	 * 
	 * @return 微信id
	 */
	@Column(name="OPEN_ID")
	public java.lang.String getOpenId() {
		return this.openId;
	}
	
	/**
	 * 设置微信id
	 * 
	 * @param openId
	 *            微信id
	 */
	public void setOpenId(java.lang.String openId) {
		this.openId = openId;
	}
	
	/**
	 * 获取发起人姓名
	 * 
	 * @return 发起人姓名
	 */
	@Column(name="NAME")
	public java.lang.String getName() {
		return this.name;
	}
	
	/**
	 * 设置发起人姓名
	 * 
	 * @param name
	 *            发起人姓名
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	/**
	 * 获取发起人电话
	 * 
	 * @return 发起人电话
	 */
	@Column(name="TEL")
	public java.lang.String getTel() {
		return this.tel;
	}
	
	/**
	 * 设置发起人电话
	 * 
	 * @param tel
	 *            发起人电话
	 */
	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}
	
	/**
	 * 获取省
	 * 
	 * @return 省
	 */
	@Column(name="PROVINCE")
	public java.lang.String getProvince() {
		return this.province;
	}
	
	/**
	 * 设置省
	 * 
	 * @param province
	 *            省
	 */
	public void setProvince(java.lang.String province) {
		this.province = province;
	}
	
	/**
	 * 获取市
	 * 
	 * @return 市
	 */
	@Column(name="CITY")
	public java.lang.String getCity() {
		return this.city;
	}
	
	/**
	 * 设置市
	 * 
	 * @param city
	 *            市
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}
	
	/**
	 * 获取区
	 * 
	 * @return 区
	 */
	@Column(name="AREA")
	public java.lang.String getArea() {
		return this.area;
	}
	
	/**
	 * 设置区
	 * 
	 * @param area
	 *            区
	 */
	public void setArea(java.lang.String area) {
		this.area = area;
	}
	
	/**
	 * 获取发起人住址
	 * 
	 * @return 发起人住址
	 */
	@Column(name="ADDRESS")
	public java.lang.String getAddress() {
		return this.address;
	}
	
	/**
	 * 设置发起人住址
	 * 
	 * @param address
	 *            发起人住址
	 */
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	/**
	 * 获取商品唯一标识
	 * 
	 * @return 商品唯一标识
	 */
	@Column(name="SKU")
	public java.lang.Long getSku() {
		return this.sku;
	}
	
	/**
	 * 设置商品唯一标识
	 * 
	 * @param sku
	 *            商品唯一标识
	 */
	public void setSku(java.lang.Long sku) {
		this.sku = sku;
	}
	
	/**
	 * 获取购买份数
	 * 
	 * @return 购买份数
	 */
	@Column(name="BUY_NUMBER")
	public java.lang.Long getBuyNumber() {
		return this.buyNumber;
	}
	
	/**
	 * 设置购买份数
	 * 
	 * @param buyNumber
	 *            购买份数
	 */
	public void setBuyNumber(java.lang.Long buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	/**
	 * 获取商品名称
	 * 
	 * @return 商品名称
	 */
	@Column(name="SKU_NAME")
	public java.lang.String getSkuName() {
		return this.skuName;
	}
	
	/**
	 * 设置商品名称
	 * 
	 * @param skuName
	 *            商品名称
	 */
	public void setSkuName(java.lang.String skuName) {
		this.skuName = skuName;
	}
	
	/**
	 * 获取商品单价
	 * 
	 * @return 商品单价
	 */
	@Column(name="GOOD_PRICE")
	public java.lang.Long getGoodPrice() {
		return this.goodPrice;
	}
	
	/**
	 * 设置商品单价
	 * 
	 * @param goodPrice
	 *            商品单价
	 */
	public void setGoodPrice(java.lang.Long goodPrice) {
		this.goodPrice = goodPrice;
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
	

	@Transient
	public String getOrderStatusStr(){
		if(this.getOrderStatus() == 0) {
			return "进行中";
		}else if(this.getOrderStatus() == 1){
			return "已完成";
		}else if(this.getOrderStatus() == 2) {
			return "已取消";
		}else {
			return "";
		}
	}
	
	@Transient
	public String getisPayStr(){
		if(this.getIsPay()==0){
			return "未支付";
		}else{
			return "已支付";
		}
	}
	

 
	@Transient
	public String getSendDateStr(){
		return DateUtil.timesampToStr(this.getSendDate(), "yyyy-MM-dd HH:mm:ss");
	}
	 
 
	@Transient
	public String getSendTimesStr(){
	 
		if(this.getSendTimes()==1){
			return "9:00-11:30";			
		}
		if(this.getSendTimes()==2){
			return "14:00-18:00";			
		}
		if(this.getSendTimes()==3){
			return "18:00-20:00";			
		}
		if(this.getSendTimes()==4){
			return "任意时间";			
		} 
		return "";
	}
	 
	@Transient
	public String getAddTimeStr(){
		return DateUtil.timesampToStr(this.getAddTime(), "yyyy-MM-dd HH:mm:ss");
	}
	 
	@Transient
	public String getEndTimeStr(){
		return DateUtil.timesampToStr(this.getEndTime(), "yyyy-MM-dd HH:mm:ss");
	} 
	@Transient
	public String getPaymenTtimeStr(){
		return DateUtil.timesampToStr(this.getPaymenTtime(), "yyyy-MM-dd HH:mm:ss");
	}
	
}