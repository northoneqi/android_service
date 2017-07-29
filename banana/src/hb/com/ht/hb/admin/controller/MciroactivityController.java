package com.ht.hb.admin.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.hb.admin.bean.Mciroactivity;
import com.ht.hb.admin.service.MciroactivityService;
import com.ht.sys.controller.BaseController;
import com.ht.sys.util.DateUtil;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;


/**
 * BackinfoTwo 访问控制层
 * <p>众筹活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:04
 */
@Controller("mciroactivityController")
@RequestMapping("admin/mciroactivity")
public class MciroactivityController extends BaseController<Mciroactivity>{
	@Resource(name = "mciroactivityServiceImpl")
	private MciroactivityService mciroactivityService; 

	/**
	 * 列表
	 */
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public String listPage(HttpServletRequest request, Model model) {
		super.setButtons(request, model, "admin/mciroactivity/listPage.do");
		return "hb/mciroactivity";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response,
			Pager<Mciroactivity> pager, String activityName, Long prizeCount, 
			String startDate, String endDate,String startTime, String endTime) {
		try{
			super.printLog(request, "查询红包活动计划列表信息");
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("activityid", "desc");
			
			if(!super.isEmpty(activityName)) {
				queryUtil.put("activityName", "like", activityName);
			}
			
			if(prizeCount != null) {
				queryUtil.put("prizeCount", "=", prizeCount);
			}
			
			if(!super.isEmpty(startDate)) {
				Timestamp time = Timestamp.valueOf(startDate); 
				queryUtil.put("beginDate", ">=", time);
			}
			
			if(!super.isEmpty(endDate)) {
				//Long date = DateUtil.stringToTimesamp(DateUtil.addDay(endDate, 1, "yyyy-MM-dd hh:mm:ss"), "yyyy-MM-dd hh:mm:ss");
				Timestamp time = Timestamp.valueOf(endDate); 
				queryUtil.put("stopDate", "<=", time);
			}
			

			if(!super.isEmpty(startTime)) {
				Long date = DateUtil.stringToTimesamp(startTime, "hh:mm:ss");
				queryUtil.put("beginTime", ">=", date);
			}
			
			if(!super.isEmpty(endTime)) {
				Long date = DateUtil.stringToTimesamp(DateUtil.addDay(endTime, 1, "hh:mm:ss"), "hh:mm:ss");
				queryUtil.put("endTime", "<", date);
			}
			
			mciroactivityService.findByPager(pager, queryUtil);
			 
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			for(Mciroactivity info: pager.getObjectList()) { 
				sbf.append("{\"activityId\":").append(info.getActivityId()).append(",")
				   .append("\"activityName\": \"").append(info.getActivityName()).append("\",")
				   .append("\"prizeCount\": ").append(info.getPrizeCount()).append(",") 
				   .append("\"peiSongCnt\": \"").append(info.getPeiSongCnt()).append("\",")
				   .append("\"sku\": \"").append(info.getSku()).append("\","); 
				if(info.getBeginDate() != null) {
					sbf.append("\"beginDate\": \"").append(info.getBeginDate()).append("\",");
				}
				
				if(info.getStopDate() != null) {
					sbf.append("\"stopDate\": \"").append(info.getStopDate()).append("\",");
				}
				
				if(info.getBeginTime() != null) {
					sbf.append("\"beginTime\": \"").append(info.getBeginTime()).append("\",");
				}
				if(info.getEndTime() != null) {
					sbf.append("\"endTime\": \"").append(info.getEndTime()).append("\",");
				} 
				sbf.append("\"showUrl\": \"").append(info.getShowUrl()).append("\"},");
				   
			}
			
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("]}"); 
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询红包活动计划列表信息");
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}
	private Date SimpleDateFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 订单统计页面
	 * */
	@RequestMapping("orderStaticsPage")
	public String orderStaticsPage(HttpServletRequest request, HttpServletResponse response, Model model){
		super.setButtons(request, model, "admin/mciroactivity/orderStaticsPage.do");
		return "hb/orderbasic_statics";
	}
	/**
	 * 订单统计
	 * 
	 * @param activityId  红包期数
	 * @param activityName 红包名称
	 * */
	@RequestMapping("orderStatics")
	public void orderStatics(HttpServletRequest request, HttpServletResponse response,
			Pager<Object[]> pager, String activityId, String activityName){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(activityId)) {
				queryUtil.put(" t2.ActivityId", "=", activityId);
			}
			
			if(!super.isEmpty(activityName)) {
				queryUtil.put("t2.ActivityName", "like", activityName);
			}
			
			System.out.println(queryUtil.getWherewhereHql());
			mciroactivityService.list(pager, queryUtil);
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			for(Object[] info: pager.getObjectList()) {
				sbf.append("{\"activityId\":\"").append(info[0]==null?"":info[0].toString()).append("\",")
				   .append("\"activityName\": \"").append(info[1]==null?"":info[1].toString()).append("\",")
				   .append("\"totalNum\": \"").append(info[2]==null?"":info[2].toString()).append("\",") 
				   .append("\"totalMoney\": \"").append(info[3]==null?"":info[3].toString()).append("\",")
				   .append("\"ycNum\": \"").append(info[4]==null?"":info[4].toString()).append("\",")  
				   .append("\"ycMoney\": \"").append(info[5]==null?"":info[5].toString()).append("\",")
				   .append("\"yyNum\": \"").append(info[6]==null?"":info[6].toString()).append("\",")
				   .append("\"yyMoney\":\"").append(info[7]==null?"":info[7].toString()).append("\",")
				   .append("\"wyNum\": \"").append(info[8]==null?"":info[8].toString()).append("\",")
				   .append("\"wyMoney\": \"").append(info[8]==null?"":info[9].toString()).append("\"},");
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
	 * 导出订单统计表
	 * */
	@RequestMapping("exportStatics")
	public void exportStaticsExcel(HttpServletRequest request, HttpServletResponse response,
			Pager<Object[]> pager, String activityId, String activityName){
		try{
			QueryUtil queryUtil = new QueryUtil();

			if(!super.isEmpty(activityId)) {
				queryUtil.put(" t2.ActivityId", "=", activityId);
			}
			
			if(!super.isEmpty(activityName)) {
				queryUtil.put("t2.ActivityName", "like", activityName);
			}
			
			mciroactivityService.list(pager, queryUtil);
			
			String config = "/WEB-INF/classes/com/ht/hb/admin/excel/mciroactivityStatics.xls";
			super.excelExport(request, response, pager.getObjectList(), "红包活动统计表", config);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg("红包活动订单导出失败");
		}
	}
	 
	 
}
