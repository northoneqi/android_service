package com.banana.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.banana.admin.bean.Sentiment;
import com.banana.admin.dao.SearchDao;
import com.ht.sys.dao.impl.BaseDaoImpl;

@Repository("SearchDao")
public class SearchDaoImpl extends BaseDaoImpl<Sentiment> implements SearchDao {

	@Override
	public List<Sentiment> queryList() {
		// TODO Auto-generated method stub
		return null;
	}

}
