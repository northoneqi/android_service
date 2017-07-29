package com.ht.sys.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.sys.annotation.Description;
import com.ht.sys.annotation.TypeEnum;
import com.ht.sys.util.DateUtil;

/** 
 * 类功能说明: 日志信息
 * <p>Title: SysLog.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午08:15:14
 * @version V1.0
 */
@Entity
@Table(name="sys_log")
public class SysLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5861322918406567310L;

	/**主键，自动增长*/
	private int id;
	
	/**生成时间*/
	@Description(value = "生成时间", type=TypeEnum.time, hidden=true)
	protected String createTime = DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss");
	
	/**备注*/
	@Description(value="备注", width=200)
	protected String remark; //备注
	
	/**用户名称*/
	private String userName;
	/**登陆ip*/
	private String loginIP;
	/**操作时间*/
	private String operateTime;
	/**操作模块*/
	private String operateModel;
	/**异常信息*/
	private String exceptionMsg;

	@Id
	// uuid生成策略,用此策略主要是防止在ext上由于id冲突导致各种问题的产生
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="f_id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=50, updatable=false, name="f_create_time")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(length=500, name="f_remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(length=50, nullable=false, name="f_user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(length=30, nullable=false, name="f_loginip")
	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	@Column(length=30, nullable=false, name="f_operate_time")
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(length=50, nullable=false, name="f_operate_model")
	public String getOperateModel() {
		return operateModel;
	}

	public void setOperateModel(String operateModel) {
		this.operateModel = operateModel;
	}

	@Column(length=5000, name="f_exception_msg")
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}
