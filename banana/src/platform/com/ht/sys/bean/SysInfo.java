package com.ht.sys.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_info")
public class SysInfo extends BaseEntity{

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	
	private static final long serialVersionUID = -2089763038281832817L;
	private String code; //单位编号
	private String name; //单位名称
	private String principal; //负责人
	private String tel; //联系电话
	private String address; //公司地址

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
