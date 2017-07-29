package com.ht.sys.bean;
 

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
 

import com.ht.sys.annotation.Description; 

/** 
 * 类功能说明: 基础实体bean
 * <p>Title:Staff.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2014</p> 
 * @author cn 
 * @date 2015-12-8 下午14：29
 * @version V1.0
 */
@Entity
@Table(name = "staff")
public class Staff extends SysEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311718731393965874L;
	
	/**所在部门*/
	@Description(value="所在部门")
	protected String departmentName;
	
	/**所在部门编码*/
	@Description(value="所在部门编码")
	protected String departmentCode;
	
	/**联系方式*/
	@Description(value = "联系方式")
	protected String phone;
	
	/**EMAIL*/
	@Description(value="EMAIL")
	protected String email; //备注

	 
	 
	 
	
	@Column(length=100, name="f_department_name")
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Column(length=100, name="f_department_code")
	public String getDepartmentCode() {
		return departmentCode;
	}
	
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode=departmentCode;
	}

	@Column(length=50, updatable=false, name="f_phone")
	 
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(length=100, name="f_EMAIL")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	 
	
}
