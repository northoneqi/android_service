package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_role_department")
public class RoleDepartment extends BaseEntity{

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	
	private static final long serialVersionUID = 1L;
	
	/**角色编号*/
	private String roleCode;
	
	/**角色名称*/
	private String roleName;
	
	/**部门编号*/
	private String departmentCode;
	
	/**部门名称*/
	private String departmentName;

	@Column(name="f_role_code", nullable=false)
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

}
