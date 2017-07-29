package com.ht.zc.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类功能说明: ProductinfoTwo
 * <p>众筹商品信息表</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-6 18:19:58
 */
@Entity
@Table(name="t_crowdfunding_productinfo_two")
public class ProductinfoTwo implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	/** 属性 */

	/** 众筹商品Id */
	private java.lang.Long crowdfundingProductId;
	
	/** 活动id */
	private java.lang.Long activityId;
	
	/** 商品唯一标识 */
	private java.lang.Long sku;
	
	/** 商品名称 */
	private java.lang.String goodName;
	
	/** 商品图片 */
	private java.lang.String goodPng;
	
	/** 商品原价格 */
	private java.lang.Double oldPrice;
	
	/** 众筹价 */
	private java.lang.Double price;
	
	/** 众筹商品数量 */
	private java.lang.Long goodNumber;
	
	/** 备注 */
	private java.lang.String remark;
	
	
	/** setter\getter方法 */
	
	/**
	 * 获取众筹商品Id
	 * 
	 * @return 众筹商品Id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CROWDFUNDING_PRODUCT_ID")
	public java.lang.Long getCrowdfundingProductId() {
		return this.crowdfundingProductId;
	}
	
	/**
	 * 设置众筹商品Id
	 * 
	 * @param crowdfundingProductId
	 *            众筹商品Id
	 */
	public void setCrowdfundingProductId(java.lang.Long crowdfundingProductId) {
		this.crowdfundingProductId = crowdfundingProductId;
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
	 * 获取商品名称
	 * 
	 * @return 商品名称
	 */
	@Column(name="GOOD_NAME")
	public java.lang.String getGoodName() {
		return this.goodName;
	}
	
	/**
	 * 设置商品名称
	 * 
	 * @param goodName
	 *            商品名称
	 */
	public void setGoodName(java.lang.String goodName) {
		this.goodName = goodName;
	}
	
	/**
	 * 获取商品图片
	 * 
	 * @return 商品图片
	 */
	@Column(name="GOOD_PNG")
	public java.lang.String getGoodPng() {
		return this.goodPng;
	}
	
	/**
	 * 设置商品图片
	 * 
	 * @param goodPng
	 *            商品图片
	 */
	public void setGoodPng(java.lang.String goodPng) {
		this.goodPng = goodPng;
	}
	
	/**
	 * 获取商品原价格
	 * 
	 * @return 商品原价格
	 */
	@Column(name="OLD_PRICE")
	public java.lang.Double getOldPrice() {
		return this.oldPrice;
	}
	
	/**
	 * 设置商品原价格
	 * 
	 * @param oldPrice
	 *            商品原价格
	 */
	public void setOldPrice(java.lang.Double oldPrice) {
		this.oldPrice = oldPrice;
	}
	
	/**
	 * 获取众筹价
	 * 
	 * @return 众筹价
	 */
	@Column(name="PRICE")
	public java.lang.Double getPrice() {
		return this.price;
	}
	
	/**
	 * 设置众筹价
	 * 
	 * @param price
	 *            众筹价
	 */
	public void setPrice(java.lang.Double price) {
		this.price = price;
	}
	
	/**
	 * 获取众筹商品数量
	 * 
	 * @return 众筹商品数量
	 */
	@Column(name="GOOD_NUMBER")
	public java.lang.Long getGoodNumber() {
		return this.goodNumber;
	}
	
	/**
	 * 设置众筹商品数量
	 * 
	 * @param goodNumber
	 *            众筹商品数量
	 */
	public void setGoodNumber(java.lang.Long goodNumber) {
		this.goodNumber = goodNumber;
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