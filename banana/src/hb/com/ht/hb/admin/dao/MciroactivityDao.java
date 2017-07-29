package com.ht.hb.admin.dao;


import com.ht.hb.admin.bean.Mciroactivity;
import com.ht.sys.dao.BaseDao; 
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/**
 * Mciroactivity 数据访问层接口
 * <p>红包活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:04
 */
public interface MciroactivityDao extends BaseDao<Mciroactivity>{
	/**
	 * 红包统计显示
	 * */
	public void  list(Pager<Object[]>  pager, QueryUtil queryUtil);
}