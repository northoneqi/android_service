package com.ht.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ht.sys.util.IReprotUtils;
import com.ht.sys.util.IReprotUtils.ExportType;

/**
 * 
 * ReportAppelt报表打印
 * @author 赵兴华
 * 
 */
@Component
@RequestMapping("report/applet/")
public class ReportAppletController {

	private static final long serialVersionUID = 1L;
	// 日志功能
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource SessionFactory sessionFactory;
	
	@RequestMapping("toPrintPage")
	public String toPrintPage(HttpServletRequest request,@RequestParam("name") String name,
			@RequestParam("number") String number,
			Model model){
		return "report/print";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("appletPrint")
	public String appletPrint(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("jaspername") String jaspername,@RequestParam("number") String number
	)throws Exception{
		
		String condition=" where 1=1 ";
		System.out.println(condition);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cims_title", "报表打印");
		params.put("serial_number", number);
		params.put("condition", condition);
		params.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report/")+"/");
		String path = FilenameUtils.normalize(request.getSession().getServletContext().getRealPath("report")+"//"+jaspername+".jasper");
		logger.debug("--------当前打印报表名称为：【"+jaspername+"】-----------");
		IReprotUtils.writeReport(path, ExportType.stream, params, sessionFactory.getCurrentSession().connection(),response);
		return null;
	}
}
