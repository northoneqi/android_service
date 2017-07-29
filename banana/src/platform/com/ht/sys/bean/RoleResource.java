/**
* @Title: RoleResource.java 
* @Package com.bluefat.sys.bean 
* @Description: TODO 
* @author qj  
* @date 2013-8-27 下午2:11:31 
* @version V1.0
*/
package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_role_resource")
public class RoleResource extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**角色*/
	private String roleCode;
	/**角色编号*/
	private String roleName;
	/**资源编号*/
	private String resourceCode;
	/**资源名称*/
	private String resourceName;
	/**父资源的编号*/
	private String parentResourceCode;
	/**资源类型*/
	private String resourceType;
	/**资源地址*/
	private String url;

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

	@Column(name="f_resource_code")
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	@Column(name="f_resource_name")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name="f_parent_resource_code")
	public String getParentResourceCode() {
		return parentResourceCode;
	}

	public void setParentResourceCode(String parentResourceCode) {
		this.parentResourceCode = parentResourceCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="f_resource_type")
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Column(name="f_url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
