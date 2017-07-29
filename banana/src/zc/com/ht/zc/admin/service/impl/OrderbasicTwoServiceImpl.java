package com.ht.zc.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.zc.admin.bean.OrderbasicTwo;
import com.ht.zc.admin.dao.OrderbasicTwoDao;
import com.ht.zc.admin.service.OrderbasicTwoService;

/**
 * OrderbasicTwo业务服务层
 * <p>众筹订单基本信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:48:33
 */
@Service("orderbasicTwoServiceImpl")
public class OrderbasicTwoServiceImpl extends BaseServiceImpl<OrderbasicTwo> implements OrderbasicTwoService{
	private OrderbasicTwoDao orderbasicTwoDao;
	
	public OrderbasicTwoServiceImpl(){
		super();
	}
	
	/**
	 * 众筹订单统计
	 * */
	public void orderStatics(Pager<Object[]> pager, QueryUtil queryUtil){
		orderbasicTwoDao.orderStatics(pager, queryUtil);
	}
	
	@Autowired
	public OrderbasicTwoServiceImpl(OrderbasicTwoDao orderbasicTwoDao){
		super.baseDao = orderbasicTwoDao;
		this.orderbasicTwoDao = orderbasicTwoDao;
	}
}