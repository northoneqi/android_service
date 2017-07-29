package com.ht.box.admin.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.box.admin.bean.MciroGrabBox;
import com.ht.box.admin.dao.PlanBoxDao;
import com.ht.box.admin.service.PlanBoxService;
import com.ht.hb.admin.dao.RedPackgeDao;
import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

@Service("PlanBoxService")
public class PlanBoxServiceImpl  extends BaseServiceImpl<MciroGrabBox> implements PlanBoxService {
	
	@Resource(name="PlanBoxDao")
	private PlanBoxDao PlanBoxDao;
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
		PlanBoxDao.list(pager, queryUtil);
	}
	@Autowired
	public void setMciroactivityDao(RedPackgeDao redPackgeDao) {
		super.baseDao =PlanBoxDao;
		this.PlanBoxDao = PlanBoxDao;
	}

}
