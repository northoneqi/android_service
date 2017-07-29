/**
* @Title: SysRoleController.java 
* @Package com.bluefat.sys.controller 
* @Description: 角色维护 
* @author qj  
* @date 2013-8-23 下午2:18:42 
* @version V1.0
*/
package com.ht.sys.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.RoleResource;
import com.ht.sys.bean.RoleDepartment;
import com.ht.sys.bean.SysResource;
import com.ht.sys.bean.SysRole;
import com.ht.sys.service.impl.RoleDepartmentService;
import com.ht.sys.service.impl.RoleResourceService;
import com.ht.sys.service.impl.SysResourceService;
import com.ht.sys.service.impl.SysRoleService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.RequestUtil;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明: 角色权限管理</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-10-30 上午10:29:47
* @version V1.0
*/
@Controller("sys_roleController")
@RequestMapping("sys/role")
public class SysRoleController extends SysController<SysRole>{
	
	private SysRoleService roleService;
	private SysResourceService resourceService;
	private RoleResourceService roleResourceService;
	private RoleDepartmentService roleDepartmentService;
	
	/**
	 * <p>角色维护页面</p>
	 * @author qj  
	 * @date 2013-8-23 下午2:21:49 
	 * @return
	 */
	@RequestMapping("listPage")
	public String roleListPage(){
		return "sys/role_list";
	}
	
	/**
	 * <p>查询角色信息</p>
	 * @author qj  
	 * @date 2013-8-23 下午2:24:38 
	 * @param request
	 * @param response
	 * @param pager
	 */
	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request, HttpServletResponse response,
			Pager<SysRole> pager){
		RequestUtil.printAttritbute(request);
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("code+0", "asc");
			roleService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
			super.printLog(request, "查询角色信息");
		}catch(Exception e){
			e.printStackTrace();
			super.setGridJson(response, "系统繁忙，请稍后再试");
			super.printLog(request, "查询角色信息", e);
		}
	}

	
	/**
	 * <p>得到所有的可用的角色下拉列表</p>
	 * @author qj
	 * @date 2013-10-30 下午12:04:12
	 * @param     
	 * @return void   
	 * @throws
	 */
	@RequestMapping("getAllRole")
	public void getAllRole(HttpServletRequest request, HttpServletResponse response){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("state", "=", "启用");
			List<SysRole> list = roleService.findByCondition(queryUtil);
			super.setAjaxMsg(response, JSONArray.fromObject(list).toString());
			super.printLog(request, "得到所有的可用的角色下拉列表");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "得到所有的可用的角色下拉列表", e);
		}
	}
	
	/**
	 * <p>得到指定角色的所有的功能权限</p>
	 * @author qj  
	 * @date 2013-8-27 下午5:32:05 
	 * @param request
	 * @param response
	 * @param roleCode    角色编号
	 * @param parentCode  父资源的编号
	 */
	@RequestMapping("getAllResource")
	public void getAllResource(HttpServletRequest request, HttpServletResponse response,
			String roleCode, String parentCode){
		try{
			if(isEmpty(parentCode)){
				parentCode = "root";
			}
			
			if(super.isEmpty(roleCode)) return;
			List<SysResource> list = roleService.getAllResource(roleCode, parentCode);
			JsonConfig config = new JsonConfig();
			//config.setExcludes(new String[]{"checked"});
			super.setAjaxMsg(response, JSONArray.fromObject(list, config).toString());
			super.printLog(request, "查询角色的功能权限信息");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询角色的功能权限信息", e);
			super.setAjaxMsg(response, false, "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * <p>得到指定角色的所有的数据权限</p>
	 * @author qj  
	 * @date 2013-8-27 下午5:32:05 
	 * @param request
	 * @param response
	 * @param roleCode    角色编号
	 * @param parentCode  部门父编号
	 */
	@RequestMapping("getAllDepartment")
	public void getAllDepartment(HttpServletRequest request, HttpServletResponse response,
			String roleCode, String parentCode){
		try{
			if(super.isEmpty(roleCode)) return;
			
			List<Department> list = roleDepartmentService.getRoleDepartment(roleCode, parentCode);
			JsonConfig config = new JsonConfig();
			super.setAjaxMsg(response, JSONArray.fromObject(list, config).toString());
			super.printLog(request, "查询角色的数据权限信息");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询角色的数据权限信息", e);
			super.setAjaxMsg(response, false, "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * <p>保存角色信息</p>
	 * @author qj  
	 * @date 2013-8-23 下午2:26:54 
	 * @param request
	 * @param response
	 * @param role  角色信息
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, SysRole role){
		try{
			roleService.save(role);
			super.printLog(request, "保存角色信息");
			super.setAjaxMsg(response, true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
			super.printLog(request, "保存角色信息", e);
		}
	}
	
	/**
	 * <p>根据角色id查找角色信息</p>
	 * @author qj  
	 * @date 2013-8-23 下午2:28:11 
	 * @param request
	 * @param response
	 * @param id   角色id
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request, HttpServletResponse response, String id){
		try{
			SysRole role = roleService.get(id);
			super.setExtJsFormJson(response, true, role);
			super.printLog(request, "根据Id查找角色");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找角色信息", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}
	
	
	/**
	 * <p>更新角色信息</p>
	 * @author qj  
	 * @date 2013-8-23 下午2:29:21 
	 * @param request
	 * @param response
	 * @param role  角色信息
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, SysRole role){
		try{
			roleService.update(role);
			super.printLog(request, "更新角色信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "更新失败! 可能原因：编号重复");
			super.printLog(request, "更新角色信息", e);
		}
	}
	
	
	/**
	 * <p>删除角色信息</p>
	 * @author qj  
	 * @date 2013-8-23 下午2:31:16 
	 * @param request
	 * @param response
	 * @param ids  角色id
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] ids){
		try{
			roleService.delete((Serializable[])ids);
			super.printLog(request, "删除角色信息");
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除角色信息", e);
			super.setAjaxMsg(response, false, "删除失败！可能原因：该角色正在被使用。");
		}
	}
	
	
	/**
	 * <p>给角色添加资源</p>
	 * @author qj
	 * @date 2013-10-30 上午10:30:33
	 * @param @param request
	 * @param @param response
	 * @param @param roleId
	 * @param @param resourceIds    
	 * @return void   
	 * @throws
	 */
	@RequestMapping("addResource")
	public void addResource(HttpServletRequest request, HttpServletResponse response,
			String roleId, String[] resourceIds){
		try{
			SysRole role = roleService.get(roleId);
			
			if(role == null) {
				super.setAjaxMsg(response, false, "添加失败，该角色可能已被删除");
				return;
			}
			
			List<RoleResource> list = new ArrayList<RoleResource>();
			for(String id: resourceIds){
				SysResource resource = resourceService.get(id);
				RoleResource roleRes = new RoleResource();
				roleRes.setRoleCode(role.getCode());
				roleRes.setRoleName(role.getName());
				roleRes.setResourceCode(resource.getCode());
				roleRes.setResourceName(resource.getName());
				roleRes.setResourceType(resource.getType());
				roleRes.setParentResourceCode(resource.getParentCode());
				roleRes.setUrl(resource.getUrl());
				list.add(roleRes);
			}
			
			roleResourceService.allotFunctionAuth(role, list);
			
			super.setAjaxMsg(response, true, "功能权限分配成功");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "给指定角色添加资源", e);
			super.setAjaxMsg(response, false, "添加失败，参数不完整");
		}
	}
	
	
	/**
	 * <p>给角色添部门</p>
	 * @author qj
	 * @date 2013-10-30 上午10:30:33
	 * @param @param request
	 * @param @param response
	 * @param @param roleId
	 * @param @param departmentIds    
	 * @return void   
	 * @throws
	 */
	@RequestMapping("addDepartment")
	public void addDepartment(HttpServletRequest request, HttpServletResponse response,
			String roleId, String[] departmentIds){
		try{
			SysRole role = roleService.get(roleId);
			
			if(role == null) {
				super.setAjaxMsg(response, false, "添加失败，该角色可能已被删除");
				return;
			}
			
			List<RoleDepartment> list = new ArrayList<RoleDepartment>();
			for(String id: departmentIds){
				Department department = departmentService.get(id);
				RoleDepartment roleDepartment = new RoleDepartment();
				roleDepartment.setRoleCode(role.getCode());
				roleDepartment.setRoleName(role.getName());
				roleDepartment.setDepartmentCode(department.getCode());
				roleDepartment.setDepartmentName(department.getName());
				list.add(roleDepartment);
			}
			
			roleDepartmentService.allotDataAuth(role, list);
			super.setAjaxMsg(response, true, "数据权限分配成功");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "给指定角色添加资源", e);
			super.setAjaxMsg(response, false, "添加失败，参数不完整");
		}
	}
	
	public SysRoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(SysRoleService roleService) {
		this.roleService = roleService;
	}

	public SysResourceService getResourceService() {
		return resourceService;
	}

	@Autowired
	public void setResourceService(SysResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public RoleResourceService getRoleResourceService() {
		return roleResourceService;
	}

	@Autowired
	public void setRoleResourceService(RoleResourceService roleResourceService) {
		this.roleResourceService = roleResourceService;
	}

	public RoleDepartmentService getRoleDepartmentService() {
		return roleDepartmentService;
	}

	@Autowired
	public void setRoleDepartmentService(RoleDepartmentService roleDepartmentService) {
		this.roleDepartmentService = roleDepartmentService;
	}
	
}
