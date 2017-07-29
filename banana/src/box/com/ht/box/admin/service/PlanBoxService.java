package com.ht.box.admin.service;

import com.ht.box.admin.bean.MciroGrabBox;
import com.ht.sys.service.BaseService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

public interface PlanBoxService extends BaseService<MciroGrabBox> {
	
	/**
	 * 抢盒子统计
	 * @param pager
	 * @param queryUtil
	 */
	public void  list(Pager<Object[]>  pager, QueryUtil queryUtil);
}
