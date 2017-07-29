package com.ht.hb.admin.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ht.hb.admin.bean.RedOrderDetail;
import com.ht.hb.admin.dao.RedPackgeDao;
import com.ht.sys.dao.impl.BaseDaoImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
@Repository("RedPackgeDapImpl")
public class RedPackgeDapImpl   extends BaseDaoImpl<RedOrderDetail> implements RedPackgeDao {

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(RedOrderDetail entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveList(List<RedOrderDetail> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(RedOrderDetail entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateList(List<RedOrderDetail> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public RedOrderDetail get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RedOrderDetail load(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RedOrderDetail> get(String[] paramString, String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RedOrderDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RedOrderDetail entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Serializable... ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String sql) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findByPager(Pager<RedOrderDetail> pager, QueryUtil queryUtil) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findByPager(Pager<RedOrderDetail> pager) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RedOrderDetail> findByCondition(QueryUtil queryUtil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void list(Pager<Object[]> pager, QueryUtil queryUtil) {
		String sql="SELECT ba.ordercode,ba.ordertype,"
				+ " ba.addtime,oa.Consignee,oa.telephone,oa.province,"
				+ " oa.city,oa.area,oa.address,de.SKUName,de.BuyPrice,"
				+ " de.Subtotal,(de.BuyPrice-de.Subtotal) a FROM "
				+ " T_OrderBasic as ba INNER JOIN T_OrderDetail as "
				+ " de  ON ba.ordercode=de.ordercode inner JOIN  "
				+ " T_OrderAddress oa ON Ba.userid=oa.userid "
				+ " WHERE 1=1 AND de.OrderCode in  "
				+ " (select OrderCode from T_OrderBasic as ba"
				+ " where Ba.Remark like '%红包%')  "+queryUtil.getWhereHql(); 
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
	String totalSql = "select count(*) from ( "+ sql +") t"; 
	Query queryTotal = getSession().createSQLQuery(totalSql.toString());
	for(int i = 0; i < queryUtil.getArrayParames().size(); i++) {
		queryTotal.setParameter(i, queryUtil.getArrayParames().get(i));
	}
	//queryTotal = getSession().createSQLQuery(totalSql.toString());
	@SuppressWarnings("unchecked")
	List<Object> totalList = queryTotal.list();
	if(totalList.size() > 0) {
		pager.setTotal(Integer.parseInt((totalList.get(0).toString())));
	}
}

}
