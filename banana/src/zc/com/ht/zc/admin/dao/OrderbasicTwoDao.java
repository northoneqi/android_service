package com.ht.zc.admin.dao;

import com.ht.sys.dao.BaseDao;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.zc.admin.bean.OrderbasicTwo;

/**
 * OrderbasicTwo 数据访问层接口
 * <p>众筹订单基本信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:48:33
 */
public interface OrderbasicTwoDao extends BaseDao<OrderbasicTwo>{

	/**
	 * 众筹订单统计
	 * */
	public void orderStatics(Pager<Object[]> pager, QueryUtil queryUtil);
}