package com.ht.hb.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.i;

import org.slf4j.impl.StaticMarkerBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.hb.admin.bean.Mciroactivity;
import com.ht.hb.admin.bean.RedOrderDetail;
import com.ht.hb.admin.service.MciroactivityService;
import com.ht.hb.admin.service.RedPackgeService;
import com.ht.sys.controller.BaseController;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

@Controller
@RequestMapping(value="admin/mciroactivity")
public class RedPackgeOrderController extends BaseController<Object> {

	@Resource(name = "RedPackgeService")
	private RedPackgeService redPackgeService ;
	/**
	 * 红包页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="listDetailPage",method={RequestMethod.GET})
	public String redpage(HttpServletRequest request,HttpServletResponse response,Model model){
		super.setButtons(request, model, "admin/mciroactivity/listPage.do");
		return "hb/redOrder";
	}
	@RequestMapping(value="redList")
	public void redPackgeDetail(HttpServletRequest request,HttpServletResponse response,
			Pager<Object[]> pager, String ordercode, String ordertype,String area,String addtime,String steptime,String telephone){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(ordercode)) {
				queryUtil.put(" ba.OrderCode", "=", ordercode);
			}
			
			if(!super.isEmpty(ordertype)) {
				queryUtil.put(" ba.OrderType", "=", ordertype);
			}
			if(!super.isEmpty(area)) {
				if(area.equals("朝阳区")){
					queryUtil.put(" oa.Area", "=", 3);		
				}else if(area.equals("东城区")){
					queryUtil.put(" oa.Area", "=", 4);						
				}else if(area.equals("西城区")){
					queryUtil.put(" oa.Area", "=", 5);
				}else if(area.equals("丰台区")){
					queryUtil.put(" oa.Area", "=", 6);
				}else if(area.equals("石景山区")){
					queryUtil.put(" oa.Area", "=", 7);
				}else if(area.equals("海淀区")){
					queryUtil.put(" oa.Area", "=", 8);
				}else if(area.equals("门头沟区")){
					queryUtil.put(" oa.Area", "=", 9);
				}else if(area.equals("房山区")){
					queryUtil.put(" oa.Area", "=", 10);				
				}else if(area.equals("通州区")){
					queryUtil.put(" oa.Area", "=", 11);
				}else if(area.equals("顺义区")){
					queryUtil.put(" oa.Area", "=", 12);
				}else if(area.equals("昌平区")){
					queryUtil.put(" oa.Area", "=", 13);
				}else if(area.equals("大兴区")){
					queryUtil.put(" oa.Area", "=", 14);
				}else if(area.equals("怀柔区")){
					queryUtil.put(" oa.Area", "=", 15);
				}else if(area.equals("平谷区")){
					queryUtil.put(" oa.Area", "=", 16);
				}
				
			}
//			if(!super.isEmpty(addtime)) {
//				queryUtil.put("ba.AddTime ", "BETWEEN", addtime);
//			}
//			if(!super.isEmpty(steptime)) {
//				queryUtil.put("", "", steptime);
//			}	
			if(!super.isEmpty(telephone)) {
				queryUtil.put(" oa.Telephone", "=", telephone);
			}
			
			System.out.println(ordercode+ordertype+area+"!!!!!!!!!!!!!222222222!!!!!!!!!"+queryUtil.getWhereHql());
			
			redPackgeService.list(pager, queryUtil);
			StringBuffer sbf = new StringBuffer();
			
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			
			for(Object[] info: pager.getObjectList()) {				
				sbf.append("{\"ordercode\": \"").append(info[0]==null?"":info[0].toString()).append("\",")
				   .append("\"ordertype\": \"").append(info[1]==null?"":info[1].toString()).append("\",") 
				   .append("\"addtime\": \"").append(info[2]==null?"":info[2].toString()).append("\",")
				   .append("\"Consignee\": \"").append(info[3]==null?"":info[3].toString()).append("\",")  
				   .append("\"telephone\": \"").append(info[4]==null?"":info[4].toString()).append("\",")
				   .append("\"province\": \"").append(info[5]==null?"":info[5].toString()).append("\",")
				   .append("\"city\":\"").append(info[6]==null?"":info[6].toString()).append("\",")
				   .append("\"area\": \"").append(info[7]==null?"":info[7].toString()).append("\",")
				   .append("\"address\": \"").append(info[8]==null?"":info[8].toString()).append("\",")
				   .append("\"SKUName\": \"").append(info[9]==null?"":info[9].toString()).append("\",")
				   .append("\"BuyPrice\": \"").append(info[10]==null?"":info[10].toString()).append("\",")
				   .append("\"Subtotal\": \"").append(info[11]==null?"":info[11].toString()).append("\",")
				   .append("\"BuyPrice\": \"").append(info[12]==null?"":info[12].toString()).append("\"},");
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
	/**
	 * 导出红包订单Excel表
	 * @param request
	 * @param response
	 * @param pager
	 * @param ordercode
	 * @param ordertype
	 * @param area
	 * @param addtime
	 * @param steptime
	 * @param telephone
	 */
	@RequestMapping("RedExportStatics")
	public void exportStaticsExcel(HttpServletRequest request, HttpServletResponse response,
			Pager<Object[]> pager, String ordercode, String ordertype,String area,String addtime,String steptime,String telephone){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(ordercode)) {
				queryUtil.put(" ba.OrderCode", "=", ordercode);
			}
			
			if(!super.isEmpty(ordertype)) {
				queryUtil.put(" ba.OrderType", "=", ordertype);
			}
			if(!super.isEmpty(area)) {
				if(area.equals("朝阳区")){
					queryUtil.put(" oa.Area", "=", 3);		
				}else if(area.equals("东城区")){
					queryUtil.put(" oa.Area", "=", 4);						
				}else if(area.equals("西城区")){
					queryUtil.put(" oa.Area", "=", 5);
				}else if(area.equals("丰台区")){
					queryUtil.put(" oa.Area", "=", 6);
				}else if(area.equals("石景山区")){
					queryUtil.put(" oa.Area", "=", 7);
				}else if(area.equals("海淀区")){
					queryUtil.put(" oa.Area", "=", 8);
				}else if(area.equals("门头沟区")){
					queryUtil.put(" oa.Area", "=", 9);
				}else if(area.equals("房山区")){
					queryUtil.put(" oa.Area", "=", 10);				
				}else if(area.equals("通州区")){
					queryUtil.put(" oa.Area", "=", 11);
				}else if(area.equals("顺义区")){
					queryUtil.put(" oa.Area", "=", 12);
				}else if(area.equals("昌平区")){
					queryUtil.put(" oa.Area", "=", 13);
				}else if(area.equals("大兴区")){
					queryUtil.put(" oa.Area", "=", 14);
				}else if(area.equals("怀柔区")){
					queryUtil.put(" oa.Area", "=", 15);
				}else if(area.equals("平谷区")){
					queryUtil.put(" oa.Area", "=", 16);
				}
				
			}
//			if(!super.isEmpty(addtime)) {
//				queryUtil.put("ba.AddTime ", "BETWEEN", addtime);
//			}
//			if(!super.isEmpty(steptime)) {
//				queryUtil.put("", "", steptime);
//			}	
			if(!super.isEmpty(telephone)) {
				queryUtil.put(" oa.Telephone", "=", telephone);
			}
			
			redPackgeService.list(pager, queryUtil);
			String config = "/WEB-INF/classes/com/ht/hb/admin/excel/RedOrderStatics.xls";
			super.excelExport(request, response, pager.getObjectList(), "红包活动订单统计表", config);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg("红包活动订单导出失败");
		}
	}
}
