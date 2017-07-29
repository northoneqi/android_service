package com.ht.zc.admin.dao;

import com.ht.sys.dao.BaseDao;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.wechat.admin.entity.ProductBasicSkuInfo;
/**
 * 商品信息
 * */
public interface ProductBasicSkuInfoDao extends BaseDao<ProductBasicSkuInfo>{
	public void  list1(Pager<Object[]> pager, QueryUtil queryUtil);

}
