package com.ht.sys.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.ht.sys.annotation.Description;
import com.ht.sys.annotation.TypeEnum;
import com.ht.sys.util.DateUtil;

/** 
 * 类功能说明: 基础实体bean
 * <p>Title: BaseBean.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-25 上午09:19:55
 * @version V1.0
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311718731393965874L;
	
	/**主键*/
	@Description(value="主键", hidden=true)
	protected String id;
	
	/**生成时间*/
	@Description(value = "生成时间", type=TypeEnum.time, hidden=true)
	protected String createTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	
	/**备注*/
	@Description(value="备注", width=200)
	protected String remark; //备注

	/**版本号*/
	private int version;
	
	@Id
	// uuid生成策略,用此策略主要是防止在ext上由于id冲突导致各种问题的产生
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length=32, name="f_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	@Column(name="f_version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
