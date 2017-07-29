package com.ht.zc.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.zc.admin.bean.ProductinfoTwo;
import com.ht.zc.admin.dao.ProductinfoTwoDao;
import com.ht.zc.admin.service.ProductinfoTwoService;

/**
 * ProductinfoTwo业务服务层
 * <p>众筹商品信息表</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:43:27
 */
@Service("productinfoTwoServiceImpl")
public class ProductinfoTwoServiceImpl extends BaseServiceImpl<ProductinfoTwo> implements ProductinfoTwoService{
	
	public ProductinfoTwoServiceImpl(){
		super();
	}
	
	@Autowired
	public ProductinfoTwoServiceImpl(ProductinfoTwoDao productinfoTwoDao){
		super.baseDao = productinfoTwoDao;
	}
}