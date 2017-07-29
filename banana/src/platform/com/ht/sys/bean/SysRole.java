package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * 类功能说明: 角色信息
 * <p>Title: SysRole.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午08:12:55
 * @version V1.0
 */
@Entity
@Table(name="sys_role")
public class SysRole extends SysEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2214524581140573577L;

	/**启用，禁用两种状态*/
	private String state;

	@Column(name="f_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
