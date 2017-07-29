package com.ht.zc.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.controller.BaseController;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.wechat.admin.entity.ProductBasicSkuInfo;
import com.ht.zc.admin.service.ProductBasicInfoService;
import com.ht.zc.admin.service.ProductBasicSkuInfoService;

/**
 * 商品信息
 * */

@Controller("productBasicSkuInfoController")
@RequestMapping("admin/productbasicskuinfo")
public class ProductBasicSkuInfoController extends BaseController<ProductBasicSkuInfo>{
	
	private ProductBasicSkuInfoService productBasicSkuInfoService;
	private ProductBasicInfoService productBasicInfoService;
	
	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response,
			Pager<Object[]> pager, String productName, Boolean isShow){
		try{
			super.printLog(request, "查询众筹活动计划列表信息");
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(productName)) {
				queryUtil.put("shopname", "like", productName);
			}
			
			if(isShow != null) {
				queryUtil.put("IsShow", "=", isShow);
			} 
			System.out.println(queryUtil.getWherewhereHql());
			productBasicSkuInfoService.list1(pager, queryUtil);
			 
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			for(Object[] info: pager.getObjectList()) {
				sbf.append("{\"SKU\": \"").append(info[5]).append("\",")
				   .append("\"ProSKUId\": \"").append(info[0]).append("\",");
				  // .append("\"productName\": \"").append(info.get()).append("\",")
				 
					if(info[1] != null) {
						sbf.append("\"productName\": \"").append(info[1]).append("\",");
					}else{
						sbf.append("\"productName\": \"").append("\",");
					}
				 
				sbf.append("\"MarketPrice\": \"").append(info[2]).append("\",")
				   .append("\"SellPrice\": \"").append(info[3]).append("\",")
				   .append("\"IsShow\": ").append(info[4]).append("},");
			}
			
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("]}");
			//System.out.println(sbf);
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询众筹活动计划列表信息");
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}

	@Autowired
	public void setProductBasicSkuInfoService(
			ProductBasicSkuInfoService productBasicSkuInfoService) {
		this.productBasicSkuInfoService = productBasicSkuInfoService;
	}

	@Autowired
	public void setProductBasicInfoService(
			ProductBasicInfoService productBasicInfoService) {
		this.productBasicInfoService = productBasicInfoService;
	}
	
	
	
}
