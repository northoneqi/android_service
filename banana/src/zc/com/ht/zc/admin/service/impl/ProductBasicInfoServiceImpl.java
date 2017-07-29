package com.ht.zc.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.wechat.admin.entity.ProductBasicInfo;
import com.ht.zc.admin.dao.ProductBasicInfoDao;
import com.ht.zc.admin.service.ProductBasicInfoService;

/**
 * 商品基础信息
 * */
@Service("productBasicInfoServiceImpl")
public class ProductBasicInfoServiceImpl extends BaseServiceImpl<ProductBasicInfo> implements ProductBasicInfoService{

	private ProductBasicInfoDao ProductBasicInfoDao;

	public ProductBasicInfoServiceImpl(){
		super();
	}
	
	@Autowired
	public void setProductBasicInfoDao(ProductBasicInfoDao productBasicInfoDao) {
		super.baseDao = productBasicInfoDao;
		ProductBasicInfoDao = productBasicInfoDao;
	}
	
	
}
