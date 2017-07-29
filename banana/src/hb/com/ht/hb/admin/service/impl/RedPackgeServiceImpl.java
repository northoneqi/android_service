package com.ht.hb.admin.service.impl;

import java.io.Serializable;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.hb.admin.bean.RedOrderDetail;
import com.ht.hb.admin.dao.RedPackgeDao;
import com.ht.hb.admin.service.RedPackgeService;
import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
@Service("RedPackgeService")
public class RedPackgeServiceImpl extends BaseServiceImpl<RedOrderDetail>  implements RedPackgeService  {
	@SuppressWarnings("unused")
	private RedPackgeDao redPackgeDao; 
	
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
		// TODO Auto-generated method stub
		redPackgeDao.list(pager, queryUtil);

	}
	@Autowired
	public void setMciroactivityDao(RedPackgeDao redPackgeDao) {
		super.baseDao =redPackgeDao;
		this.redPackgeDao = redPackgeDao;
	}
}
