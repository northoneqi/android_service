package com.ht.zc.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.controller.BaseController;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.wechat.admin.entity.ProductBasicInfo;
import com.ht.zc.admin.service.ProductBasicInfoService;

/**
 * 商品基础信息
 * */
@Controller("productBasicInfoController")
@RequestMapping("admin/productbasicinfo")
public class ProductBasicInfoController extends BaseController<ProductBasicInfo>{

	private ProductBasicInfoService productBasicInfoService;

	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response,
			Pager<ProductBasicInfo> pager, String name){
		try{
			super.printLog(request, "查询众筹活动计划列表信息");
			QueryUtil queryUtil = new QueryUtil();
			
			
			/*if(!super.isEmpty(startDate)) {
				queryUtil.put("activityAuto", "like", startDate);
			}
			
			if(!super.isEmpty(endDate)) {
				queryUtil.put("activityAuto", "like", endDate);
			}*/
			
			productBasicInfoService.findByPager(pager, queryUtil);
			
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			for(ProductBasicInfo info: pager.getObjectList()) {
				//sbf.append("{\"ProId\":").append(info.getProId()).append(",")
				   //.append("\"SKU\": \"").append(info.getSKU()).append("\",")
				  // .append("\"ProName\": \"").append(info.getProName()).append("\",")
				  // .append("\"MarketPrice\": \"").append(info.getMarketPrice()).append("\",")
				  // .append("\"SellPrice\": \"").append(info.getSellPrice()).append("\",")
				  // .append("\"IsShow\": ").append(info.isIsShow()).append("},");
			}
			
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("]}");
			
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询商品基础信息列表");
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}

	
	@Autowired
	public void setProductBasicInfoService(
			ProductBasicInfoService productBasicInfoService) {
		this.productBasicInfoService = productBasicInfoService;
	}
	
	
}
