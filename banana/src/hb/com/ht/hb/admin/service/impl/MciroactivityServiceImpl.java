package com.ht.hb.admin.service.impl;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.hb.admin.bean.Mciroactivity;
import com.ht.hb.admin.dao.MciroactivityDao;
import com.ht.hb.admin.service.MciroactivityService;
import com.ht.sys.service.impl.BaseServiceImpl; 
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/**
 * Mciroactivity 业务服务层
 * <p>红包活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:04
 */ 
@Service("mciroactivityServiceImpl")
public class MciroactivityServiceImpl extends BaseServiceImpl<Mciroactivity> implements MciroactivityService{
	private MciroactivityDao mciroactivityDao; 
	
	public MciroactivityServiceImpl(){
		super();
	}
	/**
	 * 红包统计显示
	 * */
	public void  list(Pager<Object[]>  pager, QueryUtil queryUtil) {
		mciroactivityDao.list(pager, queryUtil);	
	}
	
	@Autowired
	public void setMciroactivityDao(MciroactivityDao mciroactivityDao) {
		super.baseDao = mciroactivityDao;
		this.mciroactivityDao = mciroactivityDao;
	}
	
	
	
}