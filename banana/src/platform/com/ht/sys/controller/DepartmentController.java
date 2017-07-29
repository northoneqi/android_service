package com.ht.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.Tree;
import com.ht.sys.service.impl.DepartmentService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/** 
 * 类功能说明: 
 * <p>Title: DepartmentController.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-25 上午11:28:07
 * @version V1.0
 */
@Controller("sys_departmentController")
@RequestMapping("sys/department")
public class DepartmentController extends SysController<Department>{
	
	private DepartmentService departmentService;
	
	/**
	 * @Description: 单位添加页
	 * @author qj  
	 * @date 2013-7-25 下午03:45:41 
	 * @return
	 */
	@RequestMapping("addUnitPage")
	public String addUnitPage(){
		return "sys/unit_list";
	}
	
	/**
	 * @Description: 部门添加页
	 * @author qj  
	 * @date 2013-7-25 下午03:45:41 
	 * @return
	 */
	@RequestMapping("addDepartmentPage")
	public String addDepartmentPage(){
		return "sys/department_list";
	}
	
	/**
	 * @Description: 部门查询列表
	 * @author qj  
	 * @date 2013-7-25 下午03:46:23 
	 */
	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request, HttpServletResponse response,
		Pager<Department> pager, String unitCode, Boolean isUnit){
		try{
			super.printLog(request, "查询部门列表信息");
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("code", "!=", "1");
			queryUtil.putOrder("code", "asc");
			
			if(!isEmpty(unitCode)){
				queryUtil.put("unitCode", "=", unitCode);
			}
			
			if(isUnit != null){
				queryUtil.put("united", "=", isUnit);
			}
			
			departmentService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询部门列表信息");
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}
	
	
	/**
	 * @Description: 查询单位部门树状数据
	 * @author qj  
	 * @date 2013-8-13 上午08:45:41 
	 * @param state    状态： 默认为查询全部数据，"启用"只为查询启用的数据
	 * @param checked  查询的树是否为选择树
	 * @param pcode    父节点的编号
	 */
	@RequestMapping("queryTree")
	public void queryTree(HttpServletRequest request, HttpServletResponse response, 
			String state, String checked, String pcode, Boolean isUnit){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("code", "asc");
			
			if(!isEmpty(state)){
				queryUtil.put("state", "=", state);
			}
			
			//这个条件必须放在第一个，方便后面的查询条件修改参数值
			if(!isEmpty(pcode)){
				queryUtil.put("unitCode", "=", pcode);
			}else{
				queryUtil.put("unitCode", "=", "root");
			}
			
			if(isUnit != null){
				queryUtil.put("united", "=", isUnit);
			}
			
			Tree tree = departmentService.getTree(queryUtil);
			String str = "";
			if(isEmpty(checked)){
				JsonConfig config = new JsonConfig();
				config.setExcludes(new String[]{"checked"});
				str = JSONObject.fromObject(tree, config).toString();
			}else{
				str = JSONObject.fromObject(tree).toString();
			}
			
			//super.printLog(request, "查询部门树");
			super.setAjaxMsg(response, "["+str+"]");
		}catch(Exception e){
			e.printStackTrace();
			//super.printLog(request, "查询部门树", e);
		}
		
	}
	
	/**
	 * @Description: 保存单位部门信息
	 * @author qj  
	 * @date 2013-8-15 上午10:23:37 
	 * @param request
	 * @param response
	 * @param department  单位部门实体
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, Department department){
		try{
			departmentService.save(department);
			super.printLog(request, "添加部门");
			super.setAjaxMsg(response, true, "添加部门成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "添加部门", e);
			super.setAjaxMsg(response, false, "添加部门失败！可能原因:编号重复");
		}
	}
	
	/**
	 * @Description: 根据id查找单位部门信息
	 * @author qj  
	 * @date 2013-8-15 上午10:22:35 
	 * @param request
	 * @param response
	 * @param id  单位部门的主键
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request, HttpServletResponse response, String id){
		try{
			Department dept = departmentService.get(id);
			super.printLog(request, "根据部门ID查找部门");
			super.setExtJsFormJson(response, true, dept);
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据部门ID查找部门", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}

	/**
	 * @Description: 更新单位部门信息
	 * @author qj  
	 * @date 2013-8-15 上午10:21:17 
	 * @param request
	 * @param response
	 * @param department  单位部门信息
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, Department department){
		try{
			departmentService.update(department);
			super.printLog(request, "更新部门信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "更新部门信息", e);
			super.setAjaxMsg(response, false, "更新失败！可能原因：编号重复");
		}
	}
	
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] ids){
		try{
			departmentService.deleteDepartment(ids);
			super.printLog(request, "删除区域档案信息");
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除区域档案信息", e);
			super.setAjaxMsg(response, false, "删除失败！原因"+e.getMessage());
		}
	}
	
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
}
