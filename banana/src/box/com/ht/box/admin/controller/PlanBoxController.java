package com.ht.box.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.box.admin.bean.MciroGrabBox;
import com.ht.box.admin.service.PlanBoxService;
import com.ht.sys.controller.BaseController;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

@Controller
@RequestMapping(value="wechat/box")
public class PlanBoxController extends BaseController<MciroGrabBox> {
	
	@Resource(name="PlanBoxService")
	private PlanBoxService planBoxService;

	@RequestMapping(value="/showPage",method={RequestMethod.GET})
	public String showBoxPage(HttpServletRequest request,HttpServletResponse response,Model model){		
//		super.setButtons(request, model, "wechat/box/showPage.do");
		return "box/boxCount";
	}
	@RequestMapping(value="list" ,method={RequestMethod.POST})
	public void list(HttpServletRequest request,HttpServletResponse response,
			Pager<Object[]> pager,String activityId,String ActivityName){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(activityId)) {
				queryUtil.put(" ac.ActivityId", "=", activityId);
			}
			
			if(!super.isEmpty(ActivityName)) {
				queryUtil.put(" ac.ActivityName", "=", ActivityName);
			}
			/**
			 * 	查询统计	
			 */
			planBoxService.list(pager, queryUtil);
			
			StringBuffer sbf = new StringBuffer();
			
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			
			for(Object[] info: pager.getObjectList()) {				
				sbf.append("{\"ActivityId\": \"").append(info[0]==null?"":info[0].toString()).append("\",")
				   .append("\"ActivityName\": \"").append(info[1]==null?"":info[1].toString()).append("\",") 
				   .append("\"countAll\": \"").append(info[2]==null?"":info[2].toString()).append("\",")
				   .append("\"Send\": \"").append(info[3]==null?"":info[3].toString()).append("\",") 
				   .append("\"PeiSongCnt\": \"").append(info[4]==null?"":info[4].toString()).append("\",")
				   .append("\"BeginDate\": \"").append(info[5]==null?"":info[5].toString()).append("\",")
				   .append("\"StopDate\": \"").append(info[6]==null?"":info[6].toString()).append("\"},");
			}
			System.out.println("!!!!!!!!!!!!!!!!!!"+sbf.toString());
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
	
	@RequestMapping("exportStatics")
	public void exportStaticsExcel(HttpServletRequest request, HttpServletResponse response,
			Pager<Object[]> pager,String activityId,String ActivityName){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(activityId)) {
				queryUtil.put(" ac.ActivityId", "=", activityId);
			}
			
			if(!super.isEmpty(ActivityName)) {
				queryUtil.put(" ac.ActivityName", "=", ActivityName);
			}
			
			planBoxService.list(pager, queryUtil);
			
			String config = "/WEB-INF/classes/com/ht/hb/admin/excel/mciroactivityStatics.xls";
			super.excelExport(request, response, pager.getObjectList(), "抢盒子活动统计表", config);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg("抢盒子活动统计导出失败");
		}
	}
	
}
