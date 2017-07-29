package com.banana.admin.dao;

import java.util.List;

import com.banana.admin.bean.Sentiment;
import com.ht.sys.dao.BaseDao;

/**
 * <p>索引dao层</p>
 * @author dell
 * @data 2016-09-14
 * @version 1.0
 */
public interface SearchDao extends BaseDao<Sentiment> {
	
	public List<Sentiment> queryList();

}
