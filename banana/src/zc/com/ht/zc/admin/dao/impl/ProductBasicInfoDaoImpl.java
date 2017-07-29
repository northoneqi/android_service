package com.ht.zc.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.ht.sys.dao.impl.BaseDaoImpl;
import com.ht.wechat.admin.entity.ProductBasicInfo;
import com.ht.zc.admin.dao.ProductBasicInfoDao;

/**
 * 商品基础信息
 * */
@Repository("productBasicInfoDaoImpl")
public class ProductBasicInfoDaoImpl extends BaseDaoImpl<ProductBasicInfo> implements ProductBasicInfoDao{

}
