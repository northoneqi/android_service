package com.ht.sys.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.sys.bean.SysDataSuperpad;
import com.ht.sys.service.impl.SysDataSuperpadService;

@Controller("sys_datasuperpadController")
@RequestMapping("sys/datasuperpad")
public class SysDataSuperpadController extends BaseController{
	@Autowired private SysDataSuperpadService sysDataSuperpadService;
	/**跳转至配置界面**/
	@RequestMapping("listPage")
	public String addPage(HttpServletRequest request,Model model)throws Exception{
		String path = request.getSession().getServletContext().getRealPath("/");
		FileInputStream ips=new FileInputStream(path+"//WEB-INF/classes/databaseconfig.xml");
		model.addAttribute("sysDataSuperpad", sysDataSuperpadService.readConnection(ips));
		return "sys/datasuperpad_list";
	}
	/**测试连接是否正确**/
	@RequestMapping("testConnection")
	public void  testConnection(HttpServletResponse response,SysDataSuperpad sysDataSuperpad){
		setAjaxMsg(response,sysDataSuperpadService.testConnection(sysDataSuperpad));
	}
	/**保存正确的连接**/
	@RequestMapping("saveConnection")
	public void saveConnection(HttpServletRequest request,HttpServletResponse response,SysDataSuperpad sysDataSuperpad){
		String path = request.getSession().getServletContext().getRealPath("/");
		try {
			FileOutputStream fops=new FileOutputStream(path+"//WEB-INF/classes/databaseconfig.xml");
			setAjaxMsg(response,sysDataSuperpadService.saveConnection(sysDataSuperpad, fops));
		} catch (Exception e) {
			setAjaxMsg(response,"{success:false,msg:\""+e.getLocalizedMessage()+"\"}");
		}
	}
	
	/**将外数据传入值系统数据库**/
	@RequestMapping("leadingInConnection")
	public void leadingInConnection(HttpServletResponse response,SysDataSuperpad sysDataSuperpad,String tables){
		setAjaxMsg(response,sysDataSuperpadService.leadingInConnection(sysDataSuperpad,tables));
	}
	
	//导出数据到用友数据库中
	@RequestMapping("exportData")
	public void exportData(HttpServletResponse response,SysDataSuperpad sysDataSuperpad,
			String tables) throws Exception{
		try{
			setAjaxMsg(response, true, sysDataSuperpadService.exportData(sysDataSuperpad, tables));
		}catch(Exception e){
			e.printStackTrace();
			setAjaxMsg(response, false, "导出失败，原因:"+e.getLocalizedMessage());
		}
	}
}
