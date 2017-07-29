package com.ht.zc.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.wechat.admin.entity.ProductBasicSkuInfo;
import com.ht.zc.admin.dao.ProductBasicSkuInfoDao;
import com.ht.zc.admin.service.ProductBasicSkuInfoService;

/**
 * 商品信息
 * */

@Service("productBasicSkuInfoSerivceImpl")
public class ProductBasicSkuInfoServiceImpl extends BaseServiceImpl<ProductBasicSkuInfo> implements ProductBasicSkuInfoService{
	private ProductBasicSkuInfoDao productBasicSkuInfoDao;
	
	public ProductBasicSkuInfoServiceImpl(){
		super();
	}

	@Autowired
	public void setProductBasicSkuInfoDao(
			ProductBasicSkuInfoDao productBasicSkuInfoDao) {
		super.baseDao = productBasicSkuInfoDao;
		this.productBasicSkuInfoDao = productBasicSkuInfoDao;
	}

	@Override
	public void  list1(Pager<Object[]> pager, QueryUtil queryUtil){
		  productBasicSkuInfoDao.list1(pager, queryUtil);	
	}
 
}
