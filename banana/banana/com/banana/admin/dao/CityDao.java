package com.banana.admin.dao;
import java.util.List;

import com.banana.admin.bean.City;
import com.ht.sys.dao.BaseDao;

/**
 * 城市-DAO
 * @author qj
 * @date 2015-11-11
 * @version 1.0 
 */
public interface CityDao extends BaseDao <City> {
	
	public List<City> queryList(City city);

}
