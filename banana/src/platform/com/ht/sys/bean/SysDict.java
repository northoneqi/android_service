package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 * 类功能说明: 数据字典
 * <p>Title: SysDict.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午08:43:21
 * @version V1.0
 */
@Entity
@Table(name = "sys_dict")
public class SysDict extends SysEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8495253718185330136L;

	/**分组编号*/
	private String dCode;
	/**分组名称*/
	private String dName;
	/**状态（"启用"和"禁用"两种状态）*/
	private String state;

	@Column(name="f_d_code")
	public String getdCode() {
		return dCode;
	}

	public void setdCode(String dCode) {
		this.dCode = dCode;
	}

	@Column(name="f_d_name")
	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	@Column(name="f_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
