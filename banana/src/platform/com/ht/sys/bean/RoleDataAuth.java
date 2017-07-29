package com.ht.sys.bean;

import javax.persistence.Column;

/**
* 类功能说明: 角色的数据权限
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-12-27 下午08:38:37
* @version V1.0
*/
public class RoleDataAuth extends BaseEntity{

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	
	private static final long serialVersionUID = -8098538201971426945L;
	private String roleCode; //角色编号
	private String roleName; //角色名称
	private String resourceCode; //资源编号
	private String resourceName; //资源名称
	private String departmentCode; //部门编号
	private String departmentName; //部门名称

	@Column(nullable=false, length=20)
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(nullable=false, length=50)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(nullable=false, length=20)
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	@Column(nullable=false, length=50)
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(nullable=false, length=20)
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	@Column(nullable=false, length=50)
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
