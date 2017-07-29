package com.banana.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banana.admin.bean.City;
import com.banana.admin.dao.CityDao;
import com.banana.admin.service.CityService;
import com.ht.sys.service.impl.BaseServiceImpl;
/**
 * 城市实现类 
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
@Service("CityService")
public class CityServiceImpl extends BaseServiceImpl <City> implements CityService{

    private CityDao cityDao;

    @Autowired
	public void setCommodityRecordDao(CityDao cityDao){
		super.setBaseDao(cityDao);
        this.cityDao = cityDao;
    }

    
    public CityDao getCityDao(){
        return cityDao;
    }


	@Override
	public List<City> queryList(City city) {

		
		return cityDao.queryList(city);
	}


	@Override
	public String querySearch(Map<String, Object> map) {
		
		
		
		SearchResponse response = cityDao.search("sentiment", "dataType", map, 0, 10, "DSC", "DESC");

		System.out.println(response);
		
		return "success";
	}

}
