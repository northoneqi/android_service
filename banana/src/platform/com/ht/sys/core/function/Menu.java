package com.ht.sys.core.function;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能信息</p>
 * */
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2488582315136533595L;

	/**编号*/
	private String id;
	/**功能名称*/
	private String name;
	/**功能地址*/
	private String url;
	/**图标*/
	private String icon;
	/**子功能*/
	private List<Menu> children = new ArrayList<Menu>();
	/**拥有的按钮*/
	private List<Button> buttons = new ArrayList<Button>();

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	@Override
	public String toString() {
		String str = "{";

		str += "\"id\": "+id+",";
		if (name != null && !name.equals("")) {
			str += "\"text\": \"" + name + "\",";
		}

		if (url != null && !url.equals("")) {
			str += "\"url\": \"" + url + "\",";
		}

		if (children != null && !(children.size() == 0)) {
			str += "\"children\": " + children + ",";
		}

		
		if (str.lastIndexOf(',') != -1) {
			str = str.substring(0, str.length() - 1);
		}

		str += "}";
		return str;
	}
}
