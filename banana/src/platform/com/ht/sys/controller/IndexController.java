package com.ht.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.SysResource;
import com.ht.sys.service.impl.SysRoleService;
import com.ht.sys.util.SysHelper;
import com.ht.sys.util.db.ExecuteSQLInFile;

/** 
 * <p>类功能说明: 系统后台登陆页</p>
 * <p>Title: IndexController.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-1 下午06:33:33
 * @version V1.0
 */
@Controller("sys_indexController")
@RequestMapping("admin/")
public class IndexController extends SysController<Object>{
	private SysRoleService roleService;
	
	/**
	 * 跳转到安装界面
	 * */
	@RequestMapping("install")
	public String install(HttpServletRequest request, Model model){
		return "install";
	}
	
	@RequestMapping("initData")
	public void initData(HttpServletRequest request, HttpServletResponse response){
		try{
			ExecuteSQLInFile.excute();
			super.setAjaxMsg(response, "初始化数据成功");
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, "初始化数据失败");
		}
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model,String fcode){
		SysHelper sysHelpser = new SysHelper(request);
		sysHelpser.setRoleService(roleService);
		List<SysResource> list = sysHelpser.getMenu(fcode);
		model.addAttribute("resourceList", JSONArray.fromObject(list).toString());
		return "adminIndex";
	}
	//跳转至主页 根据用户权限 列出子系统菜单
	@RequestMapping("adminMain")
	public String adminMain(HttpServletRequest request, Model model){
		SysHelper sysHelpser = new SysHelper(request);
		sysHelpser.setRoleService(roleService);
		List<SysResource> list = sysHelpser.getNodeMenu();
		model.addAttribute("resourceList", list);
		return "adminMain";
	}
	@RequestMapping("main")
	public String main(HttpServletRequest request){
		return "main";
	}

	public SysRoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(SysRoleService roleService) {
		this.roleService = roleService;
	}
	
}
