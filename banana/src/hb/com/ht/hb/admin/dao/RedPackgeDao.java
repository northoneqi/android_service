package com.ht.hb.admin.dao;

import com.ht.hb.admin.bean.RedOrderDetail;
import com.ht.sys.dao.BaseDao;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

public interface RedPackgeDao extends BaseDao<RedOrderDetail> {

	/**
	 * 红包订单列表
	 * @param pager
	 * @param queryUtil
	 */
	public void  list(Pager<Object[]>  pager, QueryUtil queryUtil);
}
