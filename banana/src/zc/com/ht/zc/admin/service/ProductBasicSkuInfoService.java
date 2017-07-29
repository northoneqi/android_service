package com.ht.zc.admin.service;

import com.ht.sys.service.BaseService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.wechat.admin.entity.ProductBasicSkuInfo;

/**
 * 商品信息
 * */
public interface ProductBasicSkuInfoService extends BaseService<ProductBasicSkuInfo>{
	public void  list1(Pager<Object[]>  pager, QueryUtil queryUtil);
}
