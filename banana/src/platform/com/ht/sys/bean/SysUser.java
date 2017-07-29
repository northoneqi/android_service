package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * 类功能说明: 用户模型
 * <p>Title: SysUser.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午05:19:15
 * @version V1.0
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends SysEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9143654457661897063L;
	
	/**登录名*/
	private String userName;
	/**登陆密码*/
	private String password;
	/**状态(启用/禁用)*/
	private String state;
	/**部门编号*/
	private String departmentCode;
	/**部门名称*/
	private String departmentName;
	/**角色编号*/
	private String roleCode;
	/**角色名称*/
	private String roleName;
	/**数据权限*/
	//private int dataAuthCode = 4;  //数据权限有4种：本单位，本部门，本部门及子部门，本人
	//private String dataAuth; //数据权限

	/**职工姓名*/
	private String staffCode;
	/**职工编号*/
	private String staffName;
	
	@Column(name="f_user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="f_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="f_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="f_department_code")
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Column(name="f_department_name")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(name="f_staff_code")
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	@Column(name="f_staff_name")
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Column(name="f_role_code")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name="f_role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/*@Column(name="f_data_auth_code")
	public int getDataAuthCode() {
		return dataAuthCode;
	}

	public void setDataAuthCode(int dataAuthCode) {
		this.dataAuthCode = dataAuthCode;
	}

	@Column(name="f_data_auth")
	public String getDataAuth() {
		return dataAuth;
	}

	public void setDataAuth(String dataAuth) {
		this.dataAuth = dataAuth;
	}*/
	
}
