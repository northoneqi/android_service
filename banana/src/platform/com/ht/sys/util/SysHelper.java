package com.ht.sys.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.SysResource;
import com.ht.sys.bean.SysRole;
import com.ht.sys.bean.SysUser;
import com.ht.sys.service.impl.SysRoleService;

/**
 * <p>对权限系统的封装帮助类 </p>
 */
public class SysHelper {
	private HttpSession session;
	private SysRoleService roleService;
	
	public SysHelper(HttpServletRequest request){
		session = request.getSession();
	}
	/**
	 * <p>得到当前的用户信息</p>
	 * */
	public SysUser getCurrentUser(){
		SysUser user = (SysUser)session.getAttribute("loginUser");
		return user;
	}
	
	/**
	 * <p>得到当前用户所在部门</p>
	 * */
	public Department getCurrentDepartment(){
		Department department = (Department)session.getAttribute("loginDepartment");
		return department;
	}
	
	/**
	 * <p>得到当前用户的角色信息</p>
	 * */
	public SysRole getCurrentRole(){
		SysRole role = (SysRole)session.getAttribute("loginRole");
		return role;
	}
	/**列出用户所有权限菜单**/
	public List<SysResource> getMenu(String fcode){
		SysUser user = (SysUser)session.getAttribute("loginUser");
		List<SysResource> list = roleService.getMenu(user.getRoleCode(),fcode);
		return list;
	}
	/**列出用户最顶级菜单**/
	public List<SysResource> getNodeMenu(){
		SysUser user = (SysUser)session.getAttribute("loginUser");
		List<SysResource> list = roleService.getNodeMenu(user.getRoleCode());
		return list;
	}
	public SysRoleService getRoleService() {
		return roleService;
	}
	
	public void setRoleService(SysRoleService roleService) {
		this.roleService = roleService;
	}
	
}
