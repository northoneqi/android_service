package com.ht.zc.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.sys.controller.BaseController;
import com.ht.sys.util.DateUtil;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;
import com.ht.zc.admin.bean.OrderbasicTwo;
import com.ht.zc.admin.service.OrderbasicTwoService;

/**
 * OrderbasicTwo 访问控制层
 * <p>众筹订单基本信息</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:48:33
 */
@Controller("orderbasicTwoController")
@RequestMapping("admin/orderbasicTwo")
public class OrderbasicTwoController extends BaseController<OrderbasicTwo>{
	@Resource(name = "orderbasicTwoServiceImpl")
	private OrderbasicTwoService orderbasicTwoService;
 
	/**
	 * 列表
	 */
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public String listPage(HttpServletRequest request, Model model) {
		super.setButtons(request, model, "admin/orderbasicTwo/listPage.do");
		return "zc/orderbasic_two";
	}
	
	/**
	 * 列表
	 * 
	 * @param crowdfundingCode  订单编号
	 * @param orderStatus       订单状态
	 * @param area				区
	 * @param startDate			起始日期
	 * @param endDate			结束日期
	 * @param tel				联系人电话号码
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response,
			Pager<OrderbasicTwo> pager, String crowdfundingCode,
			Long orderStatus, String area, String startDate, String endDate,
			String tel) {
		try{
			super.printLog(request, "查询众筹活动订单列表信息");
			QueryUtil queryUtil = new QueryUtil();
			if(!super.isEmpty(crowdfundingCode)) {
				queryUtil.put("crowdfundingCode", "like", crowdfundingCode);
			}
			
			if(orderStatus != null) {
				queryUtil.put("orderStatus", "=", orderStatus);
			}
			
			if(!super.isEmpty(area)) {
				queryUtil.put("area", "like", area);
			}
			
			if(!super.isEmpty(tel)) {
				queryUtil.put("tel", "like", tel);
			}
			
			if(!super.isEmpty(startDate)) {
				Long date = DateUtil.stringToTimesamp(startDate, "yyyy-MM-dd");
				queryUtil.put("sendDate", ">=", date);
			}
			
			if(!super.isEmpty(endDate)) {
				Long date = DateUtil.stringToTimesamp(DateUtil.addDay(endDate, 1, "yyyy-MM-dd"), "yyyy-MM-dd");
				queryUtil.put("sendDate", "<", date);
			}
			
			orderbasicTwoService.findByPager(pager, queryUtil);
			
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			for(OrderbasicTwo info: pager.getObjectList()) {
				String remark ;
				if(info.getRemark()==null){
					 remark="";
				}else{
					remark=info.getRemark().replaceAll("(\r\n|\r|\n|\n\r)", "  ");
				} 
				sbf.append("{\"crowdfundingCode\":\"").append(info.getCrowdfundingCode()).append("\",")
				   .append("\"activityId\": \"").append(info.getActivityId()).append("\",")
				   .append("\"isPay\": \"").append(info.getIsPay()).append("\",") 
				   .append("\"orderStatus\": \"").append(info.getOrderStatus()).append("\",")
				   .append("\"payurl\": \"").append(info.getPayurl()).append("\",")  
				   .append("\"playNum\": \"").append(info.getPlayNum()).append("\",")
				   .append("\"wxOrdercode\": \"").append(info.getWxOrdercode()).append("\",")
				   .append("\"openId\":\"").append(info.getOpenId()).append("\",")
				   .append("\"name\": \"").append(info.getName()).append("\",")
				   .append("\"tel\": \"").append(info.getTel()).append("\",")
				   .append("\"province\": \"").append(info.getProvince()).append("\",")
				   .append("\"city\": \"").append(info.getCity()).append("\",")
				   .append("\"area\":\"").append(info.getArea()).append("\",")
				   .append("\"address\": \"").append(info.getAddress()).append("\",")
				   .append("\"sku\": \"").append(info.getSku()).append("\",")
				   .append("\"buyNumber\": \"").append(info.getBuyNumber()).append("\",")
				   .append("\"skuName\": \"").append(info.getSkuName()).append("\",")
				   .append("\"goodPrice\": \"").append(info.getGoodPrice()).append("\",");
				
				
				if(info.getAddTime() != null) {
					sbf.append("\"addTime\": \"").append(DateUtil.timesampToStr(info.getAddTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
				
				if(info.getEndTime() != null) {
					sbf.append("\"endTime\": \"").append(DateUtil.timesampToStr(info.getEndTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
				
				if(info.getSendDate() != null) {
					sbf.append("\"sendDate\": \"").append(DateUtil.timesampToStr(info.getSendDate(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
				
				if(info.getSendTimes() != null) {
					sbf.append("\"sendTimes\": \"").append(info.getSendTimes()).append("\",");
				}
				
				if(info.getPaymenTtime() != null) {
					sbf.append("\"paymenTtime\": \"").append(DateUtil.timesampToStr(info.getPaymenTtime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				} 
	
				sbf.append("\"remark\": \"").append(remark).append("\"},");
				
			}
			
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("]}");
			
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询众筹活动订单列表信息");
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}

	
 
	/**
	 * 列表
	 */
	@RequestMapping(value = "/find")
	public void find(HttpServletRequest request, HttpServletResponse response,
			String id) {
		try{
			super.printLog(request, "查询众筹活动订单列表信息");
			
			OrderbasicTwo info = orderbasicTwoService.get(id);
			if(info == null) {
				super.setAjaxMsg(response, "{success: false, errMessage: '查询出错'}");
				return;
			}
			
			StringBuffer sbf = new StringBuffer();
			String remark ;
			if(info.getRemark()==null){
				 remark="";
			}else{
				remark=info.getRemark().replaceAll("(\r\n|\r|\n|\n\r)", "  ");
			} 
			sbf.append("{'success': true, 'data': ");
			sbf.append("{\"crowdfundingCode\": \"").append(info.getCrowdfundingCode()).append("\",")
			   .append("\"activityId\": \"").append(info.getActivityId()).append("\",")
			   .append("\"isPay\": \"").append(info.getIsPay()).append("\",") 
			   .append("\"orderStatus\": \"").append(info.getOrderStatus()).append("\",")
			   .append("\"payurl\": \"").append(info.getPayurl()).append("\",")  
			   .append("\"playNum\": \"").append(info.getPlayNum()).append("\",")
			   .append("\"wxOrdercode\": \"").append(info.getWxOrdercode()).append("\",")
			   .append("\"openId\": \"").append(info.getOpenId()).append("\",")
			   .append("\"name\": \"").append(info.getName()).append("\",")
			   .append("\"tel\": \"").append(info.getTel()).append("\",")
			   .append("\"province\": \"").append(info.getProvince()).append("\",")
			   .append("\"city\": \"").append(info.getCity()).append("\",")
			   .append("\"area\": \"").append(info.getArea()).append("\",")
			   .append("\"address\": \"").append(info.getAddress()).append("\",")
			   .append("\"sku\": \"").append(info.getSku()).append("\",")
			   .append("\"buyNumber\": \"").append(info.getBuyNumber()).append("\",")
			   .append("\"skuName\": \"").append(info.getSkuName()).append("\",")
			   .append("\"goodPrice\": \"").append(info.getGoodPrice()).append("\",")
			   .append("\"remark\": \"").append(remark).append("\",");
			
			
			if(info.getAddTime() != null) {
				sbf.append("\"addTime\": \"").append(DateUtil.timesampToStr(info.getAddTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
			}
			
			if(info.getEndTime() != null) {
				sbf.append("\"endTime\": \"").append(DateUtil.timesampToStr(info.getEndTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
			}
			
			if(info.getSendDate() != null) {
				sbf.append("\"sendDate\": \"").append(DateUtil.timesampToStr(info.getSendDate(), "yyyy-MM-dd HH:mm:ss")).append("\",");
			}
			
			if(info.getSendTimes() != null) {
				String sentimes = null ;
				if(info.getSendTimes() == 1) {
					sentimes="9:00-11:30";
				}
				if(info.getSendTimes() == 2) {
					sentimes="14:00-18:00";
				}
				if(info.getSendTimes() == 3) {
					sentimes="18:00-20:00";
				}
				if(info.getSendTimes() == 4) {
					sentimes="任意时间";
				}
				sbf.append("\"sendTimes\": \"").append(sentimes).append("\",");
			}
			
			if(info.getPaymenTtime() != null) {
				sbf.append("\"paymenTtime\": \"").append(DateUtil.timesampToStr(info.getPaymenTtime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
			} 
			
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("}}");
			
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询众筹活动订单列表信息");
			super.setAjaxMsg(response, "{success: false, errMessage: '查询出错'}");
		}
	}
	
	
	/**
	 * 订单统计页面
	 * */
	@RequestMapping("orderStaticsPage")
	public String orderStaticsPage(HttpServletRequest request, HttpServletResponse response, Model model){
		super.setButtons(request, model, "admin/orderbasicTwo/orderStaticsPage.do");
		return "zc/orderbasic_statics";
	}
	
	/**
	 * 订单统计
	 * 
	 * @param activityId  众筹活动id
	 * @param activityNum 众筹活动期数
	 * */
	@RequestMapping("orderStatics")
	public void orderStatics(HttpServletRequest request, HttpServletResponse response,
			Pager<Object[]> pager, String activityId, String activityNum){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(activityId)) {
				queryUtil.put("t1.ACTIVITY_ID", "=", activityId);
			}
			
			if(!super.isEmpty(activityNum)) {
				queryUtil.put("t1.ACTIVITY_NUM", "like", activityNum);
			}
			
			System.out.println(queryUtil.getWherewhereHql());
			orderbasicTwoService.orderStatics(pager, queryUtil);
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			for(Object[] info: pager.getObjectList()) {
				sbf.append("{\"activituId\":\"").append(info[0]==null?"":info[0].toString()).append("\",")
				   .append("\"activityNum\": \"").append(info[1]==null?"":info[1].toString()).append("\",")
				   .append("\"playNum\": \"").append(info[2]==null?"":info[2].toString()).append("\",") 
				   .append("\"activityBeginTime\": \"").append(info[3]==null?"":info[3].toString()).append("\",")
				   .append("\"activityEndTime\": \"").append(info[4]==null?"":info[4].toString()).append("\",")  
				   .append("\"totalOrderNum\": \"").append(info[5]==null?"":info[5].toString()).append("\",")
				   .append("\"totalOrderMoney\": \"").append(info[6]==null?"":info[6].toString()).append("\",")
				   .append("\"validOrderNum\":\"").append(info[7]==null?"":info[7].toString()).append("\",")
				   .append("\"validOrderMoney\": \"").append(info[8]==null?"":info[8].toString()).append("\"},");
			}
			
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("]}");
			
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * <p>众筹活动计划导出</p>
	 * @author qj  
	 * @date 2014-11-12 上午11:51:40 
	 * @param request
	 * @param response
	 */
	@RequestMapping("export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
			String crowdfundingCode,
			Long orderStatus, String area, String startDate, String endDate,
			String tel){
		try{
			QueryUtil queryUtil = new QueryUtil();
			//queryUtil.putOrder("id", "desc");
			if(!super.isEmpty(crowdfundingCode)) {
				queryUtil.put("crowdfundingCode", "like", crowdfundingCode);
			}
			
			if(orderStatus != null) {
				queryUtil.put("orderStatus", "=", orderStatus);
			}
			
			if(!super.isEmpty(area)) {
				area = new String(area.getBytes("ISO-8859-1"), "UTF-8");
				queryUtil.put("area", "like", area);
			}
			
			if(!super.isEmpty(tel)) {
				queryUtil.put("tel", "like", tel);
			}
			
			if(!super.isEmpty(startDate)) {
				Long date = DateUtil.stringToTimesamp(startDate, "yyyy-MM-dd");
				queryUtil.put("sendDate", ">=", date);
			}
			
			if(!super.isEmpty(endDate)) {
				Long date = DateUtil.stringToTimesamp(DateUtil.addDay(endDate, 1, "yyyy-MM-dd"), "yyyy-MM-dd");
				queryUtil.put("sendDate", "<", date);
			}
			
			List<OrderbasicTwo> list = orderbasicTwoService.findByCondition(queryUtil);
			String config = "/WEB-INF/classes/com/ht/zc/admin/excel/OrderbasicTwo.xls";
			super.excelExport(request, response, list, "众筹活动订单", config);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg("众筹活动订单导出失败");
		}
	}
	
	/**
	 * 导出订单统计表
	 * */
	@RequestMapping("exportStatics")
	public void exportStaticsExcel(HttpServletRequest request, HttpServletResponse response,
			Pager<Object[]> pager, String activityId, String activityNum){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(activityId)) {
				queryUtil.put("t1.ACTIVITY_ID", "=", activityId);
			}
			
			if(!super.isEmpty(activityNum)) {
				activityNum = new String(activityNum.getBytes("ISO-8859-1"), "UTF-8");
				queryUtil.put("t1.ACTIVITY_NUM", "like", activityNum);
			}
			
			orderbasicTwoService.orderStatics(pager, queryUtil);
			
			String config = "/WEB-INF/classes/com/ht/zc/admin/excel/OrderbasicTwoStatics.xls";
			super.excelExport(request, response, pager.getObjectList(), "众筹活动统计表", config);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg("众筹活动订单导出失败");
		}
	}
	 
}
