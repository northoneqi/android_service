package com.ht.hb.admin.service;

import java.io.Serializable;

import com.ht.hb.admin.bean.Mciroactivity;
import com.ht.sys.service.BaseService; 
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/**
 * Mciroactivity业务服务层接口
 * <p>红包活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:05
 */
public interface MciroactivityService extends BaseService<Mciroactivity>{
	/**
	 * 红包统计显示
	 * */
	public void  list(Pager<Object[]>  pager, QueryUtil queryUtil);
}