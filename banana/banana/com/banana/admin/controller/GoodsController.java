package com.banana.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banana.admin.bean.City;
import com.banana.admin.bean.Goods;
import com.banana.admin.service.GoodsService;
import com.ht.sys.controller.BaseController;
import com.ht.sys.util.Pager;
import com.ht.sys.util.QueryConditionUtils;
import com.ht.sys.util.db.QueryUtil;

/**
 * android：商品 控制层
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController<Goods> {

	@Autowired
	@Qualifier("GoodsService")
	private GoodsService goodsService;
	
	@RequestMapping("/find")
	public void queryDate(HttpServletRequest request, HttpServletResponse response,Pager<Goods> pager,Goods goods){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String quest = request.getRequestURL().toString();
		
		QueryUtil queryUtil = new QueryUtil();
		queryUtil = QueryConditionUtils.queryCondition(goods, queryUtil);
		
		goodsService.findByPager(pager, queryUtil);
		pager.setEvent("0");
		pager.setMessage("成功返回");	
		super.putAndroidJson(response, pager);
		
	}
	
}
