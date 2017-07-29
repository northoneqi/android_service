package com.banana.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.banana.admin.bean.City;
import com.banana.admin.dao.CityDao;
import com.ht.sys.dao.impl.BaseDaoImpl;

/**
 * 城市-DAO实现类
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
@Repository("CityDao")
public class CityDaoImpl extends BaseDaoImpl <City> implements CityDao{

	@Override
	public List<City> queryList(City city) {

		
		
		
		return null;
	}

}
