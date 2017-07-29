package com.ht.sys.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.sys.bean.mapper.SnType;

/**
* 类功能说明: 编号生成规则
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-26 下午02:11:50
* @version V1.0
*/
@Entity
@Table(name="sys_sn")
public class Sn implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 **/

	private static final long serialVersionUID = -6732969111082508338L;

	private int id;			//主键
	private SnType snType;  //编号类型
	private int maxValue;  //最大值
	private String date;	//添加日期
	private String prefix = ""; //前缀
	private String suffix = ""; //后缀
	private int length = 4; //序列号长度
	private String rule= "yyyyMMdd"; //编号规则

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(updatable=false, unique=true, nullable=false)
	public SnType getSnType() {
		return snType;
	}


	public void setSnType(SnType snType) {
		this.snType = snType;
	}
	

	@Column(nullable=false, length=10)
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	@Column(length=10, nullable=false)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Column(length=20)
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column(length=20)
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Column(length=20)
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
	
}
