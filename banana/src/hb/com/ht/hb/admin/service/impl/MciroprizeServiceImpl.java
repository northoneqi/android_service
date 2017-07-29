package com.ht.hb.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.hb.admin.bean.Mciroprize;
import com.ht.hb.admin.dao.MciroprizeDao;
import com.ht.hb.admin.service.MciroprizeService;
import com.ht.sys.service.impl.BaseServiceImpl;

/**
 * Mciroprize业务服务层
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-19 14:08:01
 */
@Service("mciroprizeServiceImpl")
public class MciroprizeServiceImpl extends BaseServiceImpl<Mciroprize> implements MciroprizeService{
		
	public MciroprizeServiceImpl(){
		super();
	}
	
	@Autowired
	public MciroprizeServiceImpl(MciroprizeDao mciroprizeDao){
		super.baseDao = mciroprizeDao;
	}
}