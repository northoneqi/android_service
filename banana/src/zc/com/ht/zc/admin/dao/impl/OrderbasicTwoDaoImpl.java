package com.ht.zc.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.ht.sys.dao.impl.BaseDaoImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.zc.admin.bean.OrderbasicTwo;
import com.ht.zc.admin.dao.OrderbasicTwoDao;

/**
 * OrderbasicTwo数据访问层
 * <p>众筹订单基本信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:48:33
 */
@Repository("orderbasicTwoDaoImpl")
public class OrderbasicTwoDaoImpl extends BaseDaoImpl<OrderbasicTwo> implements OrderbasicTwoDao{
	
	/**
	 * 众筹订单统计
	 * */
	public void orderStatics(Pager<Object[]> pager, QueryUtil queryUtil){
		String sql=" select t1.ACTIVITY_ID, t1.ACTIVITY_NUM, t1.PLAY_NUM, t1.ACTIVITY_BEGIN_TIME, ACTIVITY_END_TIME, "
		     +" count(t2.CROWDFUNDING_CODE) totalOrderNum, sum(t2.BUY_NUMBER*t2.GOOD_PRICE) totalOrderMoney, "
		     +" sum((case when t2.ORDER_STATUS = 2 then 1 else 0 end)) as validOrderNum, "
		     +" sum((case when t2.ORDER_STATUS = 2 then t2.BUY_NUMBER*t2.GOOD_PRICE else 0 end)) as validOrderMoney " +
		     " from t_crowdfunding_backInfo_two t1 left join t_crowdfunding_orderbasic_two t2 " +
		     " on(t1.ACTIVITY_ID = t2.ACTIVITY_ID) " +
		     " where 1=1 "+queryUtil.getWhereHql()+
		     " group by t1.ACTIVITY_ID, t1.ACTIVITY_NUM, t1.PLAY_NUM, t1.ACTIVITY_BEGIN_TIME, t1.ACTIVITY_END_TIME ";
		Query query=getSession().createSQLQuery(sql.toString());
		for(int i = 0; i < queryUtil.getArrayParames().size(); i++) {
			query.setParameter(i, queryUtil.getArrayParames().get(i));
		}
		
		if(pager.getStart() != -1) {
			query.setFirstResult(pager.getStart()).setMaxResults(pager.getLimit());
		}
		List<Object[]> list=query.list();
		pager.setResult(list);
		
		//统计总数量
		String totalSql = "select count(*) from ( "+ sql +") t "; 
		Query queryTotal = getSession().createSQLQuery(totalSql.toString());
		for(int i = 0; i < queryUtil.getArrayParames().size(); i++) {
			queryTotal.setParameter(i, queryUtil.getArrayParames().get(i));
		}
		//queryTotal = getSession().createSQLQuery(totalSql.toString());
		List<Object> totalList = queryTotal.list();
		if(totalList.size() > 0) {
			pager.setTotal(Integer.parseInt((totalList.get(0).toString())));
		}
	}
}