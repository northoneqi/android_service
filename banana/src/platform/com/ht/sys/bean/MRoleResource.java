/**
* @Title: RoleResource.java 
* @Package com.bluefat.sys.bean 
* @Description: TODO 
* @author qj  
* @date 2013-8-27 下午2:11:31 
* @version V1.0
*/
package com.ht.sys.bean;

public class MRoleResource {

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
	/**资源地址*/
	private String url;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
