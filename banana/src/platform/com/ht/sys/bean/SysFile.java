/**
* @Title: SysFile.java 
* @Package com.bluefat.sys.bean 
* @Description: 附件
* @author qj  
* @date 2013-9-11 上午11:31:13 
* @version V1.0
*/
package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 类功能说明: 文件管理模块，主要用于文件上传下载
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午09:18:45
* @version V1.0
*/
@Entity
@Table(name="sys_file")
public class SysFile extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8645423513990070881L;
	
	/**附件组号*/
	private String code;
	/**附件名称*/
	private String name;
	/**附件大小*/
	private double size;
	/**类型*/
	private String type;
	/**总页数*/
	private int totalPage;
	/**附件上传后的名称*/
	private String newName;

	
	@Column(length=20, unique=false, nullable=false, name="f_code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="f_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="f_size")
	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	@Column(name="f_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="f_new_name")
	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	@Column(name="f_total_page")
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
