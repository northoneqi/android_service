package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/** 
 * 类功能说明: 资源信息类（包括菜单，按钮等资源）
 * <p>Title: SysResource.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-11 下午06:23:50
 * @version V1.0
 */

@Entity
@Table(name="sys_resource")
public class SysResource extends SysEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5398890053050115760L;

	/**类型（accordion,menu, button）*/
	private String type;
	/**只有menu才有这种类型*/
	private String url;
	/**只有button才有这种处理方式*/
	private String handler;
	/**如果是menu，那么就是指它的上一层菜单，如果是button那么就是指这个button所属的菜单*/
	private String parentCode;
	/**'启用'和‘禁用’两种状态*/
	private String state;
	/**图标*/
	private String icon;
	/**是否为叶子节点 */
	private boolean leaf = false;
	/**类型名称*/
	private String typeName;
	/**是否选中*/
	private boolean checked;

	@Column(length = 50, name="f_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length=200, name="f_url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length=2000, name="f_handler")
	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	@Column(length=20, name="f_parent_code")
	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(length=10, name="f_state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(length=50, name="f_icon")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Transient
	public boolean getLeaf() {
		if("button".equals(this.type)){
			this.leaf = true;
		}
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Transient
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Transient
	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
