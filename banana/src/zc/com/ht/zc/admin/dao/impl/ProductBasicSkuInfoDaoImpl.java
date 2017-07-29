package com.ht.zc.admin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ht.sys.dao.impl.BaseDaoImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.wechat.admin.entity.ProductBasicSkuInfo;
import com.ht.zc.admin.dao.ProductBasicSkuInfoDao;
 
/**
 * 商品信息
 * */
@Repository("productBasicSkuInfoDaoImpl")
public class ProductBasicSkuInfoDaoImpl extends BaseDaoImpl<ProductBasicSkuInfo> implements ProductBasicSkuInfoDao{

	public void  list1(Pager<Object[]> pager, QueryUtil queryUtil){
		String sql=" select sku,shopname,marketPrice,sellprice,isShow,proskuid from ( "+
					"select (case when a.WXProName IS null then b.ProName "+
					" when a.WXProName = '' then b.ProName "+
					" else a.WXProName end) shopname,a.*"+
					"from T_ProductBasicSkuInfo a "+
					"right join T_ProductBasicInfo b "+
					"on a.ProId=b.ProId"+
					") c  where 1=1 "+queryUtil.getWhereHql();
		 
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