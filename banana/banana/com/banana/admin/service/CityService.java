package com.banana.admin.service;

import java.util.List;
import java.util.Map;

import com.banana.admin.bean.City;
import com.ht.sys.service.BaseService;

/**
 * 城市-Service
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
public interface CityService extends BaseService <City> {
	
	public List<City> queryList(City city);
	
	
	public String querySearch(Map<String, Object> map);

}
