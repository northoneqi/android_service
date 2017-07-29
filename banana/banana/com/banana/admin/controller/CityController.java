package com.banana.admin.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banana.admin.bean.City;
import com.banana.admin.service.CityService;
import com.ht.sys.controller.BaseController;
import com.ht.sys.util.ElasticsearchUtil;
import com.ht.sys.util.Pager;
import com.ht.sys.util.QueryConditionUtils;
import com.ht.sys.util.db.QueryUtil;


/**
 * android: 
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
@Controller
@RequestMapping("/city")
public class CityController extends BaseController<City>{

	private CityService cityService;

	@RequestMapping("/find")
	public void queryDate(HttpServletRequest request, HttpServletResponse response,Pager<City> pager,City city){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String quest = request.getRequestURL().toString();
		
		QueryUtil queryUtil = new QueryUtil();
		queryUtil = QueryConditionUtils.queryCondition(city, queryUtil);
		
		cityService.findByPager(pager, queryUtil);
		pager.setEvent("0");
		pager.setMessage("成功返回");
		super.putAndroidJson(response, pager);
		
	}
	
	@RequestMapping("/queryList")
	public void queryList(HttpServletRequest request, HttpServletResponse response,Pager<City> pager,City city){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
/*		String quest = request.getRequestURL().toString();
		
		QueryUtil queryUtil = new QueryUtil();
		queryUtil = QueryConditionUtils.queryCondition(city, queryUtil);
		
		//cityService.queryList(City);
		pager.setEvent("0");
		pager.setMessage("成功返回");
		super.putAndroidJson(response, pager);*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("title", "我们");
		
		cityService.querySearch(map);

	}
	
	
	public CityService getCityService() {
		return cityService;
	}
	@Autowired
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	
	
}
