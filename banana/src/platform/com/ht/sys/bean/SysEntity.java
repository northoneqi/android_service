package com.ht.sys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/** 
 * 类功能说明: 系统核心超类
 * <p>Title: SysEntity.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午02:23:02
 * @version V1.0
 */

@MappedSuperclass
public class SysEntity extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1318357145274229470L;

	/**编码（唯一）*/
	private String code;
	/**名称*/
	private String name;

	@Column(length=20, unique=true, nullable=false, name="f_code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length=100, nullable=false, name="f_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
