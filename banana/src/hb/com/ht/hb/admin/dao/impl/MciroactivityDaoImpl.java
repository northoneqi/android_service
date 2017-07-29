package com.ht.hb.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ht.hb.admin.bean.Mciroactivity;
import com.ht.hb.admin.dao.MciroactivityDao;
import com.ht.sys.dao.impl.BaseDaoImpl; 
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;


/**
 * Mciroactivity 数据访问层
 * <p>红包活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:04
 */
@Repository("mciroactivityDaoImpl")
public class MciroactivityDaoImpl extends BaseDaoImpl<Mciroactivity> implements MciroactivityDao{
	/**
	 * 红包统计显示
	 * */
	public void  list(Pager<Object[]> pager, QueryUtil queryUtil){
		String sql="select  T2.ActivityId ,ActivityName ,count (PrizeContent) totalNum,SUM( CONVERT(int,PrizeContent)) totalMoney,"+
					"sum(case WHEN note = 1 then 1 ELSE 0 end) ycNum,"+
					"SUM(case WHEN note = 1 then CONVERT(int,PrizeContent )ELSE 0 end) ycMoney,"+
					"sum(case WHEN note = 1 and  CardState = 1 then 1 ELSE 0 end) yyNum,"+
					"SUM(case WHEN note = 1 and  CardState = 1 then CONVERT(int,PrizeContent )ELSE 0 end) yyMoney,"+
					"sum(case WHEN note = 1 and  CardState = 0 then 1 ELSE 0 end) wyNum,"+
					"SUM(case WHEN note = 1 and  CardState = 0 then CONVERT(int,PrizeContent )ELSE 0 end) wyMoney "+
					"from T_MciroPrize t1 left join T_MciroActivity t2 on t1.ActivityId=t2.ActivityId  where 1=1 "+queryUtil.getWhereHql()+
					"GROUP BY t2.ActivityId,t2.ActivityName "; 
		System.out.println("sql---->"+sql);
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