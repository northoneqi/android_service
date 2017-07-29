package com.ht.zc.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类功能说明: PayinfoTwo
 * <p>众筹支付信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-6 18:30:53
 */
@Entity
@Table(name="t_crowdfunding_payinfo_two")
public class PayinfoTwo implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	/** 属性 */

	/** Id */
	private java.lang.Long crowdfundingId;
	
	/** 众筹订单编号 */
	private java.lang.String crowdfundingCode;
	
	/** 支付人openid */
	private java.lang.String payOpenId;
	
	/** 支付人姓名 */
	private java.lang.String payName;
	
	/** 支付金额 */
	private java.lang.Double payMoney;
	
	/** 支付时间 */
	private java.lang.Long addTime;
	
	/** 交易出号 */
	private java.lang.String outTradeNo;
	
	/** 运输费 */
	private java.lang.Long transportFee;
	
	/** 交易状态 */
	private java.lang.Long tradeState;
	
	/** 交易模式 */
	private java.lang.Long tradeMode;
	
	/** 合作人 */
	private java.lang.String partner;
	
	/** 银行类型 */
	private java.lang.String bankType;
	
	/** 银行编号 */
	private java.lang.String bankBillno;
	
	/** 总价 */
	private java.lang.Long totalFee;
	
	/** 资金类型 */
	private java.lang.Long feeType;
	
	/** 公告id */
	private java.lang.String notifyId;
	
	/** 事务id */
	private java.lang.String transactionId;
	
	/** 结束时间 */
	private java.lang.Long timeEnd;
	
	/** 产品费用 */
	private java.lang.Long productFee;
	
	/** 折扣 */
	private java.lang.Long discount;
	
	/** 退款状态 */
	private java.lang.Long refundStatus;
	
	/** 备注 */
	private java.lang.String remark;
	
	
	/** setter\getter方法 */
	
	/**
	 * 获取Id
	 * 
	 * @return Id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CROWDFUNDING_ID")
	public java.lang.Long getCrowdfundingId() {
		return this.crowdfundingId;
	}
	
	/**
	 * 设置Id
	 * 
	 * @param crowdfundingId
	 *            Id
	 */
	public void setCrowdfundingId(java.lang.Long crowdfundingId) {
		this.crowdfundingId = crowdfundingId;
	}
	
	/**
	 * 获取众筹订单编号
	 * 
	 * @return 众筹订单编号
	 */
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
	 * 获取支付人openid
	 * 
	 * @return 支付人openid
	 */
	@Column(name="PAY_OPEN_ID")
	public java.lang.String getPayOpenId() {
		return this.payOpenId;
	}
	
	/**
	 * 设置支付人openid
	 * 
	 * @param payOpenId
	 *            支付人openid
	 */
	public void setPayOpenId(java.lang.String payOpenId) {
		this.payOpenId = payOpenId;
	}
	
	/**
	 * 获取支付人姓名
	 * 
	 * @return 支付人姓名
	 */
	@Column(name="PAY_NAME")
	public java.lang.String getPayName() {
		return this.payName;
	}
	
	/**
	 * 设置支付人姓名
	 * 
	 * @param payName
	 *            支付人姓名
	 */
	public void setPayName(java.lang.String payName) {
		this.payName = payName;
	}
	
	/**
	 * 获取支付金额
	 * 
	 * @return 支付金额
	 */
	@Column(name="PAY_MONEY")
	public java.lang.Double getPayMoney() {
		return this.payMoney;
	}
	
	/**
	 * 设置支付金额
	 * 
	 * @param payMoney
	 *            支付金额
	 */
	public void setPayMoney(java.lang.Double payMoney) {
		this.payMoney = payMoney;
	}
	
	/**
	 * 获取支付时间
	 * 
	 * @return 支付时间
	 */
	@Column(name="ADD_TIME")
	public java.lang.Long getAddTime() {
		return this.addTime;
	}
	
	/**
	 * 设置支付时间
	 * 
	 * @param addTime
	 *            支付时间
	 */
	public void setAddTime(java.lang.Long addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * 获取交易出号
	 * 
	 * @return 交易出号
	 */
	@Column(name="OUT_TRADE_NO")
	public java.lang.String getOutTradeNo() {
		return this.outTradeNo;
	}
	
	/**
	 * 设置交易出号
	 * 
	 * @param outTradeNo
	 *            交易出号
	 */
	public void setOutTradeNo(java.lang.String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	/**
	 * 获取运输费
	 * 
	 * @return 运输费
	 */
	@Column(name="TRANSPORT_FEE")
	public java.lang.Long getTransportFee() {
		return this.transportFee;
	}
	
	/**
	 * 设置运输费
	 * 
	 * @param transportFee
	 *            运输费
	 */
	public void setTransportFee(java.lang.Long transportFee) {
		this.transportFee = transportFee;
	}
	
	/**
	 * 获取交易状态
	 * 
	 * @return 交易状态
	 */
	@Column(name="TRADE_STATE") 
	public java.lang.Long getTradeState() {
		return this.tradeState;
	}
	
	/**
	 * 设置交易状态
	 * 
	 * @param tradeState
	 *            交易状态
	 */
	public void setTradeState(java.lang.Long tradeState) {
		this.tradeState = tradeState;
	}
	
	/**
	 * 获取交易模式
	 * 
	 * @return 交易模式
	 */
	@Column(name="TRADE_MODE")
	public java.lang.Long getTradeMode() {
		return this.tradeMode;
	}
	
	/**
	 * 设置交易模式
	 * 
	 * @param tradeMode
	 *            交易模式
	 */
	public void setTradeMode(java.lang.Long tradeMode) {
		this.tradeMode = tradeMode;
	}
	
	/**
	 * 获取合作人
	 * 
	 * @return 合作人
	 */
	@Column(name="PARTNER")
	public java.lang.String getPartner() {
		return this.partner;
	}
	
	/**
	 * 设置合作人
	 * 
	 * @param partner
	 *            合作人
	 */
	public void setPartner(java.lang.String partner) {
		this.partner = partner;
	}
	
	/**
	 * 获取银行类型
	 * 
	 * @return 银行类型
	 */
	@Column(name="BANK_TYPE")
	public java.lang.String getBankType() {
		return this.bankType;
	}
	
	/**
	 * 设置银行类型
	 * 
	 * @param bankType
	 *            银行类型
	 */
	public void setBankType(java.lang.String bankType) {
		this.bankType = bankType;
	}
	
	/**
	 * 获取银行编号
	 * 
	 * @return 银行编号
	 */
	@Column(name="BANK_BILLNO")
	public java.lang.String getBankBillno() {
		return this.bankBillno;
	}
	
	/**
	 * 设置银行编号
	 * 
	 * @param bankBillno
	 *            银行编号
	 */
	public void setBankBillno(java.lang.String bankBillno) {
		this.bankBillno = bankBillno;
	}
	
	/**
	 * 获取总价
	 * 
	 * @return 总价
	 */
	@Column(name="TOTAL_FEE")
	public java.lang.Long getTotalFee() {
		return this.totalFee;
	}
	
	/**
	 * 设置总价
	 * 
	 * @param totalFee
	 *            总价
	 */
	public void setTotalFee(java.lang.Long totalFee) {
		this.totalFee = totalFee;
	}
	
	/**
	 * 获取资金类型
	 * 
	 * @return 资金类型
	 */
	@Column(name="FEE_TYPE")
	public java.lang.Long getFeeType() {
		return this.feeType;
	}
	
	/**
	 * 设置资金类型
	 * 
	 * @param feeType
	 *            资金类型
	 */
	public void setFeeType(java.lang.Long feeType) {
		this.feeType = feeType;
	}
	
	/**
	 * 获取公告id
	 * 
	 * @return 公告id
	 */
	@Column(name="NOTIFY_ID")
	public java.lang.String getNotifyId() {
		return this.notifyId;
	}
	
	/**
	 * 设置公告id
	 * 
	 * @param notifyId
	 *            公告id
	 */
	
	public void setNotifyId(java.lang.String notifyId) {
		this.notifyId = notifyId;
	}
	
	/**
	 * 获取事务id
	 * 
	 * @return 事务id
	 */
	@Column(name="TRANSACTION_ID")
	public java.lang.String getTransactionId() {
		return this.transactionId;
	}
	
	/**
	 * 设置事务id
	 * 
	 * @param transactionId
	 *            事务id
	 */
	public void setTransactionId(java.lang.String transactionId) {
		this.transactionId = transactionId;
	}
	
	/**
	 * 获取结束时间
	 * 
	 * @return 结束时间
	 */
	@Column(name="TIME_END")
	public java.lang.Long getTimeEnd() {
		return this.timeEnd;
	}
	
	/**
	 * 设置结束时间
	 * 
	 * @param timeEnd
	 *            结束时间
	 */
	public void setTimeEnd(java.lang.Long timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	/**
	 * 获取产品费用
	 * 
	 * @return 产品费用
	 */
	@Column(name="PRODUCT_FEE")
	public java.lang.Long getProductFee() {
		return this.productFee;
	}
	
	/**
	 * 设置产品费用
	 * 
	 * @param productFee
	 *            产品费用
	 */
	public void setProductFee(java.lang.Long productFee) {
		this.productFee = productFee;
	}
	
	/**
	 * 获取折扣
	 * 
	 * @return 折扣
	 */
	@Column(name="DISCOUNT")
	public java.lang.Long getDiscount() {
		return this.discount;
	}
	
	/**
	 * 设置折扣
	 * 
	 * @param discount
	 *            折扣
	 */
	public void setDiscount(java.lang.Long discount) {
		this.discount = discount;
	}
	
	/**
	 * 获取退款状态
	 * 
	 * @return 退款状态
	 */
	@Column(name="REFUND_STATUS")
	public java.lang.Long getRefundStatus() {
		return this.refundStatus;
	}
	
	/**
	 * 设置退款状态
	 * 
	 * @param refundStatus
	 *            退款状态
	 */
	public void setRefundStatus(java.lang.Long refundStatus) {
		this.refundStatus = refundStatus;
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
	

}