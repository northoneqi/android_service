package com.ht.sys.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.SysUser;
import com.ht.sys.service.impl.SysUserService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/** 
 * <p>类功能说明: 用户管理模块控制类</p>
 * <p>Title: SysUserController.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-4 下午02:41:00
 * @version V1.0
 */

@Controller("sys_userController")
@RequestMapping("sys/user")
public class SysUserController extends SysController<SysUser>{

	private PasswordEncoder passwordEncoder;
	private SysUserService userService;
	
	/**
	 * <p>添加用户界面</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:31:09 
	 * @return
	 */
	@RequestMapping("addPage")
	public String addPage(){
		return "sys/user_list";
	}
	
	/**
	 * <p>用户查询</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:32:33 
	 * @param request
	 * @param response
	 * @param userName     用户名
	 * @param departmentName  用户所在部门
	 */
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			Pager<SysUser> pager, String userName, String departmentCode) {
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
			
			userService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "用户查询", e);
			super.setGridJson(response, "查询出现异常，请联系管理员");
		}
	}
	
	
	/**
	 * <p>根据部门编号查询该部门的人员下拉框数据</p>
	 * @author qj
	 * @date 2013-11-22 下午10:29:13
	 * @param @param request
	 * @param @param response
	 * @param @param departmentCode    部门编号
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
			List<SysUser> list = userService.findByCondition(queryUtil);
			super.setAjaxMsg(response, JSONArray.fromObject(list).toString());
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询人员下拉框", e);
		}
	}
	
	/**
	 * <p>保存用户信息</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:41:34 
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, SysUser user){
		try{
			String password = passwordEncoder.encodePassword("0000", user.getUserName());
			user.setPassword(password);
			userService.save(user);
			super.printLog(request, "保存用户信息");
			super.setAjaxMsg(response, true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "保存用户信息", e);
			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * <p>根据id查找用户信息</p>
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
			SysUser user = userService.get(id);
			super.printLog(request, "根据id查找用户信息");
			super.setExtJsFormJson(response, true, user);
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找用户信息", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}
	
	
	/**
	 * <p> 更新用户信息</p>
	 * @author qj  
	 * @date 2013-9-15 上午11:42:32 
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, SysUser user){
		try{
			SysUser oldUser = userService.get(user.getId());
			user.setPassword(oldUser.getPassword());
			userService.update(user);
			super.printLog(request, "更新用户信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "更新用户信息", e);
			super.setAjaxMsg(response, false, "更新失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * <p>删除用户信息 </p>
	 * @author qj  
	 * @date 2013-9-15 上午11:45:44 
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] ids){
		try{
			userService.delete((Serializable[])ids);
			super.printLog(request, "删除用户信息");
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除用户信息", e);
			super.setAjaxMsg(response, false, "删除失败！可能原因：该用户正在被使用。");
		}
	}

	/**
	 * <p>修改密码</p>
	 * @param request
	 * @param response
	 * @param id
	 * @param oldPassword		原密码
	 * @param newPassword		新密码
	 * @param confirmPassword	确定密码
	 */
	@RequestMapping("updatePassword")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response,
			String id, String oldPassword, String newPassword, String confirmPassword){
		try{
			SysUser user = userService.get(id);
			
			if(user.getPassword().equals(passwordEncoder.encodePassword(oldPassword, user.getUserName()))){
				if(oldPassword.equals(newPassword)){
					super.setAjaxMsg(response, "新密码与旧密码相同,请认真填写");
				}else if(!newPassword.equals(confirmPassword) ){
					super.setAjaxMsg(response, "两次密码不一直,请认真填写");
				}else{
					user.setPassword(passwordEncoder.encodePassword(newPassword, user.getUserName()));
					userService.update(user);
					request.getSession().setAttribute("loginUser", user);
					super.setAjaxMsg(response, "修改密码成功");
				}
			}else{
				super.setAjaxMsg(response, "密码不对，请确认密码");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public SysUserService getUserService() {
		return userService;
	}

	
	@Autowired
	public void setUserService(SysUserService userService) {
		this.userService = userService;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
}
