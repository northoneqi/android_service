package com.ht.sys.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.SysLog;
import com.ht.sys.util.DateUtil;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/** 
 * <p>类功能说明: 日志控制类</p>
 * <p>Title: SysLogController.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午08:59:43
 * @version V1.0
 */
@Controller("sys_logController")
@RequestMapping("sys/log/")
public class SysLogController extends SysController<SysLog>{


	/**
	 * <p>日志列表页</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:26:58 
	 * @return
	 */
	@RequestMapping("listPage")
	public String logListPage(){
		return "sys/log_list";
	}
	
	/**
	 * <p>查询日志信息</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:27:57 
	 * @param pager  分页信息
	 */
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			Pager<SysLog> pager, String userName,
			String startDate, String endDate){
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			if(!super.isEmpty(userName)){
				queryUtil.put("userName", "=", userName);
			}
			
			if(!super.isEmpty(startDate)){
				queryUtil.put("operateTime", ">=", startDate);
			}
			
			if(!super.isEmpty(endDate)){
				endDate = DateUtil.addDay(endDate, 1, "yyyy-MM-dd");
				queryUtil.put("operateTime", "<=", endDate);
			}
			
			logService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
			logger.info("查询日志信息");
		}catch(Exception e){
			e.printStackTrace();
			super.setGridJson(response, "系统繁忙，请稍后再试");
			super.printLog(request, "查询日志信息异常【"+e.getMessage()+"】");
		}
	}
	
	/**
	 * <p>删除日志信息</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:28:59 
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, 
			Integer[] ids){
		try{
			if(ids == null || ids.length == 0){
				logService.delete();
			}else {
				logService.delete((Serializable[])ids);
			}
			super.setAjaxMsg(response, false, "删除成功！");
			super.printLog(request, "删除日志信息");
			
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除日志信息", e);
			super.setAjaxMsg(response, false, "删除失败！");
		}
	}
	
	/**
	 * <p>系统日志导出</p>
	 * @author qj  
	 * @date 2013-9-12 上午11:51:40 
	 * @param request
	 * @param response
	 */
	@RequestMapping("export")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response){
		try{
			List<SysLog> list = logService.getAll();
			String config = "/WEB-INF/classes/com/bluefat/sys/excel/log.xls";
			super.excelExport(request, response, list, "系统日志", config);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg("日志导出失败");
		}
	}
}
