package com.ht.zc.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.zc.admin.bean.PayinfoTwo;
import com.ht.zc.admin.dao.PayinfoTwoDao;
import com.ht.zc.admin.service.PayinfoTwoService;

/**
 * PayinfoTwo业务服务层
 * <p>众筹支付信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-6 18:30:53
 */
@Service("payinfoTwoServiceImpl")
public class PayinfoTwoServiceImpl extends BaseServiceImpl<PayinfoTwo> implements PayinfoTwoService{
		
	public PayinfoTwoServiceImpl(){
		super();
	}
	
	@Autowired
	public PayinfoTwoServiceImpl(PayinfoTwoDao payinfoTwoDao){
		super.baseDao = payinfoTwoDao;
	}
}