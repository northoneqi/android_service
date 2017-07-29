package com.ht.box.admin.dao;

import com.ht.box.admin.bean.MciroGrabBox;
import com.ht.sys.dao.BaseDao;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

public interface PlanBoxDao extends BaseDao<MciroGrabBox> {
	/**
	 * 抢盒子统计
	 * @param pager
	 * @param queryUtil
	 */
	public void list(Pager<Object[]> pager, QueryUtil queryUtil);
}
