package com.ht.sys.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.Staff;
import com.ht.sys.service.impl.StaffService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/** 
 * <p>类功能说明: 职工管理模块控制类</p>
 * <p>Title: SysUserController.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-4 下午02:41:00
 * @version V1.0
 */

@Controller("staffController")
@RequestMapping("sys/staff")
public class StaffController extends SysController<Staff>{

	private StaffService staffService;
	

	/**
	 * <p>添加职工界面</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:31:09 
	 * @return
	 */
	@RequestMapping("addPage")
	public String addPage(){
		return "sys/staff_list";
	}
	
	/**
	 * <p>职工查询</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:32:33 
	 * @param request
	 * @param response
	 * @param Name     职工名
	 * @param departmentName  职工所在职工
	 */
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			Pager<Staff> pager, String userName, String departmentCode) {
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			queryUtil.putOrder("departmentCode", "asc");
			queryUtil.putOrder("code", "asc");
			
			if(!isEmpty(userName)) {
				queryUtil.put("name", "like", userName);
			}
			
			if(!isEmpty(departmentCode)){
				queryUtil.put("departmentCode", "like", departmentCode);
			}
			
			staffService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "职工查询", e);
			super.setGridJson(response, "查询出现异常，请联系管理员");
		}
	}
	
	
	/**
	 * <p>根据职工编号查询该职工的人员下拉框数据</p>
	 * @author qj
	 * @date 2013-11-22 下午10:29:13
	 * @param @param request
	 * @param @param response
	 * @param @param departmentCode    职工编号
	 * @return void   
	 * @throws
	 */
	@RequestMapping("getCombo")
	public void getCombo(HttpServletRequest request, HttpServletResponse response,
			String departmentCode){
		try{
			QueryUtil queryUtil = new QueryUtil();
			if(!super.isEmpty(departmentCode)) {
				queryUtil.put("departmentCode", "=", departmentCode);
			}
			List<Staff> list = staffService.findByCondition(queryUtil);
			super.setAjaxMsg(response, JSONArray.fromObject(list).toString());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询人员下拉框", e);
		}
	}
	
	/**
	 * <p>保存职工信息</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:41:34 
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, Staff staff){
		try{
			staffService.save(staff);
			super.printLog(request, "保存职工信息");
			super.setAjaxMsg(response, true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "保存职工信息", e);
			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * <p>根据id查找职工信息</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:44:31 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request, HttpServletResponse response,
			String id){
		try{
			Staff user = staffService.get(id);
			super.printLog(request, "根据id查找职工信息");
			super.setExtJsFormJson(response, true, user);
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找职工信息", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}
	
	
	/**
	 * <p> 更新职工信息</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:42:32 
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, Staff staff){
		try{
			Staff oldUser = staffService.get(staff.getId()); 
			staffService.update(staff);
			super.printLog(request, "更新职工信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "更新职工信息", e);
			super.setAjaxMsg(response, false, "更新失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * <p>删除职工信息 </p>
	 * @author qj  
	 * @date 2013-9-15 上午11:45:44 
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] ids){
		try{
			staffService.delete((Serializable[])ids);
			super.printLog(request, "删除职工信息");
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除职工信息", e);
			super.setAjaxMsg(response, false, "删除失败！可能原因：该职工正在被使用。");
		}
	}
	
	
	public StaffService getStaffService() {
		return staffService;
	}

	
	@Autowired
	public void StaffService(StaffService staffService) {
		this.staffService = staffService; 
	}
 
}
