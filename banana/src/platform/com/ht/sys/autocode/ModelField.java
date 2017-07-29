package com.ht.sys.autocode;

/**
* 类功能说明: extjs 映射field
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-7 下午01:55:27
* @version V1.0
*/
public class ModelField {

	/**显示字段*/
	private String fieldLabel;
	/**name值*/
	private String name;
	/**列表中的宽度*/
	private int width;
	/**是否隐藏*/
	private boolean hidden;
	/**类型*/
	private String type;

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean getHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String str = "{fieldLabel: '"+fieldLabel+"',"+
					"name: '"+name+"',"+
					"width: "+width+","+
					"hidden: "+hidden+","+
					"type: '"+type+"'}";
		return str;
	}

}
