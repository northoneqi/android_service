package com.ht.zc.admin.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.ht.sys.controller.BaseController;
import com.ht.sys.util.DateUtil;
import com.ht.sys.util.FileUtil;
import com.ht.sys.util.Pager;
import com.ht.sys.util.PropertyReader;
import com.ht.sys.util.db.QueryUtil;
import com.ht.wechat.admin.entity.ProductBasicInfo;
import com.ht.wechat.admin.entity.ProductBasicSkuInfo;
import com.ht.zc.admin.bean.BackinfoTwo;
import com.ht.zc.admin.bean.ProductinfoTwo;
import com.ht.zc.admin.service.BackinfoTwoService;
import com.ht.zc.admin.service.ProductBasicInfoService;
import com.ht.zc.admin.service.ProductBasicSkuInfoService;
import com.ht.zc.admin.service.ProductinfoTwoService;

/**
 * BackinfoTwo 访问控制层
 * <p>众筹活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:04
 */
@Controller("backinfoTwoController")
@RequestMapping("admin/backinfoTwo")
public class BackinfoTwoController extends BaseController<BackinfoTwo>{
	@Resource(name = "backinfoTwoServiceImpl")
	private BackinfoTwoService backinfoTwoService;
	@Resource(name="productBasicSkuInfoSerivceImpl")
	private ProductBasicSkuInfoService productBasicSkuInfoService;
	@Resource(name="productBasicInfoServiceImpl")
	private ProductBasicInfoService productBasicInfoService;
	@Resource(name="productinfoTwoServiceImpl")
	private ProductinfoTwoService productinfoTwoService;
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response, BackinfoTwo backinfoTwo,
			String activityAddTimes, String activityBeginTimes,  String productName,
			String activityEndTimes, Integer productId,
			Long productNum, Double productPrice, String productRemark) {
		try{
			if(activityBeginTimes != null) {
				backinfoTwo.setActivityBeginTime(DateUtil.stringToTimesamp(activityBeginTimes, "yyyy-MM-dd HH:mm:ss"));
			}
			
			if(activityEndTimes != null) {
				backinfoTwo.setActivityEndTime(DateUtil.stringToTimesamp(activityEndTimes, "yyyy-MM-dd HH:mm:ss"));
			}
			
			if(productId == null){
				super.setAjaxMsg(response, false, "添加失败，商品不存在！");
				return;
			}
			
			ProductBasicSkuInfo productBasicSkuInfo = productBasicSkuInfoService.get(productId);
			if(productBasicSkuInfo == null) {
				super.setAjaxMsg(response, false, "添加失败，商品不存在！");
				return;
			}
			System.out.println(super.getRootPath());
			DefaultMultipartHttpServletRequest  multipartRequest = (DefaultMultipartHttpServletRequest ) request;
			String rootPath = PropertyReader.read(super.getRootPath()+"/classes/application.properties", "product_image_path");
			String fileName = FileUtil.save(multipartRequest, super.getRootPath()+"../upload/");
			System.out.println(rootPath+"/upload/"+fileName);
			ProductinfoTwo productinfoTwo = new ProductinfoTwo();
			productinfoTwo.setSku(new Long(productBasicSkuInfo.getSKU()));
			productinfoTwo.setGoodName(productName);
			if(productBasicSkuInfo.getWxProName() == null || productBasicSkuInfo.getWxProName().equals("")){
				ProductBasicInfo productBasicInfo = productBasicInfoService.get(productBasicSkuInfo.getProSKUId());
				if(productBasicInfo != null) {
					productinfoTwo.setGoodName(productBasicInfo.getProName());
				}else{
					productinfoTwo.setGoodName("未知");
				}
			}else{
				productinfoTwo.setGoodName(productBasicSkuInfo.getWxProName());
			}
			productinfoTwo.setGoodPng(rootPath+"/upload/"+fileName);
			productinfoTwo.setOldPrice(productBasicSkuInfo.getSellPrice());
			productinfoTwo.setPrice(productPrice);
			productinfoTwo.setGoodNumber(productNum);
			productinfoTwo.setRemark(productRemark);
			
			Long addTime = DateUtil.stringToTimesamp(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
			backinfoTwo.setActivityAddTime(addTime);
			backinfoTwoService.save(backinfoTwo, productinfoTwo);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "添加失败！");
		}
		super.setAjaxMsg(response, true, "添加成功！");
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response, BackinfoTwo backinfoTwo,
			String activityAddTimes, String activityBeginTimes, String productName,
			String activityEndTimes, Integer productId,
			Long productNum, Double productPrice, String productImg, String productRemark) {
		try{
			BackinfoTwo pBackinfoTwo = backinfoTwoService.get(backinfoTwo.getId());
			if (pBackinfoTwo == null ) {
				super.setAjaxMsg(response, false, "没有找到该众筹活动计划！");
			}
			
			if(activityBeginTimes != null) {
				backinfoTwo.setActivityBeginTime(DateUtil.stringToTimesamp(activityBeginTimes, "yyyy-MM-dd HH:mm:ss"));
			}
			
			if(activityEndTimes != null) {
				backinfoTwo.setActivityEndTime(DateUtil.stringToTimesamp(activityEndTimes, "yyyy-MM-dd HH:mm:ss"));
			}
			
			backinfoTwo.setActivityAddTime(pBackinfoTwo.getActivityAddTime());
			
			if(productId == null){
				super.setAjaxMsg(response, false, "添加失败，商品不存在！");
				return;
			}
			
			ProductBasicSkuInfo productBasicSkuInfo = productBasicSkuInfoService.get(productId);
			if(productBasicSkuInfo == null) {
				super.setAjaxMsg(response, false, "添加失败，商品不存在！");
				return;
			}
			
			DefaultMultipartHttpServletRequest  multipartRequest = (DefaultMultipartHttpServletRequest ) request;
	        String rootPath = PropertyReader.read(super.getRootPath()+"/classes/application.properties", "product_image_path");
			String fileName = FileUtil.save(multipartRequest, super.getRootPath()+"../upload/");
			System.out.println(fileName);
			ProductinfoTwo productinfoTwo = new ProductinfoTwo();
			productinfoTwo.setSku(new Long(productBasicSkuInfo.getSKU()));
			/*if(productBasicSkuInfo.getWxProName() == null || productBasicSkuInfo.getWxProName().equals("")){
				ProductBasicInfo productBasicInfo = productBasicInfoService.get(productBasicSkuInfo.getProSKUId());
				if(productBasicInfo != null) {
					productinfoTwo.setGoodName(productBasicInfo.getProName());
				}else{
					productinfoTwo.setGoodName("");
				}
			}else{
				productinfoTwo.setGoodName(productBasicSkuInfo.getWxProName());
			}*/
			
			
			productinfoTwo.setGoodName(productName);
			
			if(fileName == null || fileName.equals("")) {
				productinfoTwo.setGoodPng(productImg);
			}else{
				productinfoTwo.setGoodPng(rootPath+"/upload/"+fileName);
			}
			
			productinfoTwo.setOldPrice(productBasicSkuInfo.getSellPrice());
			productinfoTwo.setPrice(productPrice);
			productinfoTwo.setGoodNumber(productNum);
			productinfoTwo.setRemark(productRemark);
			
			backinfoTwoService.update(backinfoTwo, productinfoTwo);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "更新失败！");
		}
		
		super.setAjaxMsg(response, true, "更新成功！");
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public String listPage(HttpServletRequest request, Model model) {
		super.setButtons(request, model, "admin/backinfoTwo/listPage.do");
		return "zc/backinfoTwo";
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request, HttpServletResponse response,
			Pager<BackinfoTwo> pager, String activityNum, Long activityStatus, 
			String activityAuto, String startDate, String endDate) {
		try{
			super.printLog(request, "查询众筹活动计划列表信息");
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("id", "desc");
			if(!super.isEmpty(activityNum)) {
				queryUtil.put("activityNum", "like", activityNum);
			}
			
			if(activityStatus != null) {
				queryUtil.put("activityStatus", "=", activityStatus);
			}
			
			if(!super.isEmpty(activityAuto)) {
				queryUtil.put("activityAuto", "like", activityAuto);
			}
			
			if(!super.isEmpty(startDate)) {
				Long date = DateUtil.stringToTimesamp(startDate, "yyyy-MM-dd");
				queryUtil.put("activityBeginTime", ">=", date);
			}
			
			if(!super.isEmpty(endDate)) {
				Long date = DateUtil.stringToTimesamp(DateUtil.addDay(endDate, 1, "yyyy-MM-dd"), "yyyy-MM-dd");
				queryUtil.put("activityEndTime", "<", date);
			}
			
			backinfoTwoService.findByPager(pager, queryUtil);
			 
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'total':").append(pager.getTotal()).append(",'success': true, 'root': [");
			for(BackinfoTwo info: pager.getObjectList()) {
				String  activityInfo=info.getActivityInfo().replaceAll("(\r\n|\r|\n|\n\r)", "  ");
				String remark=info.getRemark().replaceAll("(\r\n|\r|\n|\n\r)", "  ");
				sbf.append("{\"id\":").append(info.getId()).append(",")
				   .append("\"activityNum\": \"").append(info.getActivityNum()).append("\",")
				   .append("\"activityStatus\": ").append(info.getActivityStatus()).append(",") 
				   .append("\"activityInfo\": \"").append(activityInfo).append("\",")
				   .append("\"playNum\": \"").append(info.getPlayNum()).append("\",")
				   .append("\"isCanal\": ").append(info.getIsCanal()).append(",")
				   .append("\"isPayall\": ").append(info.getIsPayall()).append(",")
				   .append("\"minPayNumber\":").append(info.getMinPayNumber()).append(",")
				   .append("\"maxPlayMore\": ").append(info.getMaxPlayMore()).append(",")
				   .append("\"deliveryUpLimit\":").append(info.getDeliveryUpLimit()).append(",")
				   .append("\"launchUpLimit\": ").append(info.getLaunchUpLimit()).append(",")
				   .append("\"maxPayMore\": ").append(info.getMaxPayMore()).append(",");
				if(info.getActivityBeginTime() != null) {
					sbf.append("\"activityBeginTime\": \"").append(DateUtil.timesampToStr(info.getActivityBeginTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
				
				if(info.getActivityBeginTime() != null) {
					sbf.append("\"activityEndTime\": \"").append(DateUtil.timesampToStr(info.getActivityEndTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
				
				if(info.getActivityAddTime() != null) {
					sbf.append("\"activityAddTime\": \"").append(DateUtil.timesampToStr(info.getActivityAddTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
				}
				
				sbf.append("\"activityAuto\": \"").append(info.getActivityAuto()).append("\",");
				   
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
			super.printLog(request, "查询众筹活动计划列表信息");
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * 根据id查找众筹活动
	 */
	@RequestMapping(value = "/find")
	public void find(HttpServletRequest request, HttpServletResponse response,
			Long id) {
		try{
			super.printLog(request, "查询众筹活动计划列表信息");
			
			BackinfoTwo info = backinfoTwoService.get(id);
			if(info == null) {
				super.setAjaxMsg(response, "{success: false, errMessage: '查询出错'}");
				return;
			}
			
			String  activityInfo=info.getActivityInfo().replaceAll("(\r\n|\r|\n|\n\r)", "  ");
			String remark=info.getRemark().replaceAll("(\r\n|\r|\n|\n\r)", "  ");
			
			StringBuffer sbf = new StringBuffer();
			sbf.append("{'success': true, 'data': ");
			sbf.append("{\"id\":").append(info.getId()).append(",")
			   .append("\"activityNum\": \"").append(info.getActivityNum()).append("\",")
			   .append("\"activityStatus\": ").append(info.getActivityStatus()).append(",")
			   .append("\"activityInfo\": \"").append(activityInfo).append("\",")
			   .append("\"playNum\": \"").append(info.getPlayNum()).append("\",")
			   .append("\"isCanal\": ").append(info.getIsCanal()).append(",")
			   .append("\"isPayall\": ").append(info.getIsPayall()).append(",")
			   .append("\"minPayNumber\":").append(info.getMinPayNumber()).append(",")
			   .append("\"maxPlayMore\": ").append(info.getMaxPlayMore()).append(",")
			   .append("\"deliveryUpLimit\":").append(info.getDeliveryUpLimit()).append(",")
			   .append("\"launchUpLimit\": ").append(info.getLaunchUpLimit()).append(",")
			   .append("\"maxPayMore\": ").append(info.getMaxPayMore()).append(",");
			if(info.getActivityBeginTime() != null) {
				sbf.append("\"activityBeginTimes\": \"").append(DateUtil.timesampToStr(info.getActivityBeginTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
			}
			
			if(info.getActivityBeginTime() != null) {
				sbf.append("\"activityEndTimes\": \"").append(DateUtil.timesampToStr(info.getActivityEndTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
			}
			
			if(info.getActivityAddTime() != null) {
				sbf.append("\"activityAddTimes\": \"").append(DateUtil.timesampToStr(info.getActivityAddTime(), "yyyy-MM-dd HH:mm:ss")).append("\",");
			}
			
			sbf.append("\"activityAuto\": \"").append(info.getActivityAuto()).append("\",");
			sbf.append("\"remark\": \"").append(remark).append("\", ");
			
			//查询商品信息
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("activityId", "=", info.getId());
			List<ProductinfoTwo> list = productinfoTwoService.findByCondition(queryUtil);
			if(list.size() > 0) {
				ProductinfoTwo productinfoTwo = list.get(0);
				String productRemark = productinfoTwo.getRemark().replaceAll("(\r\n|\r|\n|\n\r)", "  "); 
				sbf.append("\"productName\": \"").append(productinfoTwo.getGoodName()).append("\", ");
				sbf.append("\"productImg\": \"").append(productinfoTwo.getGoodPng()).append("\", ");
				sbf.append("\"productNum\": \"").append(productinfoTwo.getGoodNumber()).append("\", ");
				sbf.append("\"productOldPrice\": \"").append(productinfoTwo.getOldPrice()).append("\", ");
				sbf.append("\"productPrice\": \"").append(productinfoTwo.getPrice()).append("\", ");
				sbf.append("\"productId\": \"").append(productinfoTwo.getSku()).append("\", ");
				sbf.append("\"productRemark\": \"").append(productRemark).append("\",");
			}
				   
			
			if(sbf.lastIndexOf(",") == (sbf.length()-1)) {
				sbf = sbf.deleteCharAt(sbf.length()-1);
			}
			
			sbf.append("}}");
			
			super.setAjaxMsg(response, sbf.toString());
			//super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询众筹活动计划列表信息");
			super.setAjaxMsg(response, "{success: false, errMessage: '查询出错'}");
		}
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(Long[] ids, HttpServletResponse response) {
		if (ids != null) {
			backinfoTwoService.deleteBackInfo((Serializable[])ids);
			super.setAjaxMsg(response, true, "删除成功");
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
			String activityNum, Long activityStatus, 
			String activityAuto, String startDate, String endDate){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("id", "desc");
			if(!super.isEmpty(activityNum)) {
				activityNum = new String(activityNum.getBytes("ISO-8859-1"), "UTF-8");
				queryUtil.put("activityNum", "like", activityNum);
			}
			
			if(activityStatus != null) {
				queryUtil.put("activityStatus", "=", activityStatus);
			}
			
			if(!super.isEmpty(activityAuto)) {
				activityAuto = new String(activityAuto.getBytes("ISO-8859-1"), "UTF-8");
				queryUtil.put("activityAuto", "like", activityAuto);
			}
			
			if(!super.isEmpty(startDate)) {
				Long date = DateUtil.stringToTimesamp(startDate, "yyyy-MM-dd");
				queryUtil.put("activityBeginTime", ">=", date);
			}
			
			if(!super.isEmpty(endDate)) {
				Long date = DateUtil.stringToTimesamp(DateUtil.addDay(endDate, 1, "yyyy-MM-dd"), "yyyy-MM-dd");
				queryUtil.put("activityEndTime", "<", date);
			}
			
			List<BackinfoTwo> list = backinfoTwoService.findByCondition(queryUtil);
			String config = "/WEB-INF/classes/com/ht/zc/admin/excel/BackinfoTwo.xls";
			super.excelExport(request, response, list, "众筹活动计划", config);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg("日志导出失败");
		}
	}
	
}
