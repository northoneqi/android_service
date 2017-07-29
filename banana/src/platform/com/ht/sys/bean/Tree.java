package com.ht.sys.bean;

import java.util.ArrayList;

/** 
 * 类功能说明: 对extjs的树基本数据格式的封装
 * <p>Title: BaseTree.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-13 上午08:55:30
 * @version V1.0
 */

public class Tree {

	private String id;
	private String text;
	private String icon;
	private String code;
	private String state;
	private boolean checked = false;
	private boolean editable = false; 
	private boolean leaf = false;
	private ArrayList<Tree> children = new ArrayList<Tree>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	
	public boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public ArrayList<Tree> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Tree> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
