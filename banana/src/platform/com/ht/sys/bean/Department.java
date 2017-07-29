package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/** 
 * 类功能说明: 单位部门实体
 * <p>Title: Department.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-24 下午05:03:57
 * @version V1.0
 */

@Entity
@Table(name="sys_department")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="testCache")
public class Department extends SysEntity {

	/**
	 * 自动产生的序列号
	 */
	private static final long serialVersionUID = -5838553742147145055L;
	
	/**是否为部门*/
	private boolean united = false;
	/**所属单位编码*/
	private String unitCode; 
	/**所属单位名称*/
	private String unitName; 
	/**状态（启用/禁用*/
	private String state;
	
	/**是否为叶子节点 */
	private boolean leaf = false;
	/**是否选中*/
	private boolean checked;
	

	@Column(nullable = false, name="f_united")
	public boolean getUnited() {
		return united;
	}

	public void setUnited(boolean united) {
		this.united = united;
	}

	@Column(nullable = false, name="f_unit_code")
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	@Column(nullable = false, name="f_unit_name")
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(nullable = false, name="f_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Transient
	public boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Transient
	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
