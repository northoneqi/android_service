package com.ht.zc.admin.service;

import com.ht.sys.service.BaseService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.zc.admin.bean.OrderbasicTwo;

/**
 * OrderbasicTwo业务服务层接口
 * <p>众筹订单基本信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:48:33
 */
public interface OrderbasicTwoService extends BaseService<OrderbasicTwo>{

	/**
	 * 众筹订单统计
	 * */
	public void orderStatics(Pager<Object[]> pager, QueryUtil queryUtil);
}