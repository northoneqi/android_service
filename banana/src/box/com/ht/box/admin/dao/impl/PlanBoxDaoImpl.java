package com.ht.box.admin.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ht.box.admin.bean.MciroGrabBox;
import com.ht.box.admin.dao.PlanBoxDao;
import com.ht.sys.dao.impl.BaseDaoImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
@Repository("PlanBoxDao")
public class PlanBoxDaoImpl extends BaseDaoImpl<MciroGrabBox> implements PlanBoxDao {

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(MciroGrabBox entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveList(List<MciroGrabBox> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MciroGrabBox entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateList(List<MciroGrabBox> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public MciroGrabBox get(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MciroGrabBox load(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MciroGrabBox> get(String[] paramString, String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MciroGrabBox> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MciroGrabBox entity) {
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
	public void findByPager(Pager<MciroGrabBox> pager, QueryUtil queryUtil) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findByPager(Pager<MciroGrabBox> pager) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MciroGrabBox> findByCondition(QueryUtil queryUtil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void list(Pager<Object[]> pager, QueryUtil queryUtil) {
		String sql="SELECT	ac.ActivityId,ac.ActivityName,COUNT (*) as countAll,"
					+ " COUNT(gb.PeisongStatus)as Send,ac.PeiSongCnt,ac.BeginDate,ac.StopDate FROM	T_MciroGrabBox AS gb"
					+ " inner JOIN T_MciroActivity AS ac ON gb.ActivityId = ac.ActivityId"
					+ " WHERE 1=1  "+queryUtil.getWhereHql()+" GROUP BY	gb.PeisongStatus, ac.ActivityId, ac.ActivityName,"
					+ " ac.PeiSongCnt , ac.BeginDate , ac.StopDate"; 
	Query query=getSession().createSQLQuery(sql.toString());
	for(int i = 0; i < queryUtil.getArrayParames().size(); i++) {
		query.setParameter(i, queryUtil.getArrayParames().get(i));
	}

	if(pager.getStart() != -1) {
		query.setFirstResult(pager.getStart()).setMaxResults(pager.getLimit());
	}
	@SuppressWarnings("unchecked")
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
