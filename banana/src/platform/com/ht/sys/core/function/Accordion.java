package com.ht.sys.core.function;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

/** 
 * <p>类功能说明: 每个子系统</p>
 * <p>Title: Accordion.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-11 下午06:46:29
 * @version V1.0
 */

public class Accordion {
	
	private String id;
	private String name;
	@XStreamImplicit(itemFieldName="menu")
	private List<Menu> menus = new ArrayList<Menu>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
