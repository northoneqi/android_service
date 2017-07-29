package com.ht.sys.springmvc.editor;

import java.beans.PropertyEditorSupport;

/**
* <p>类功能说明: 单据类型属性编辑器</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-11 下午07:33:10
* @version V1.0
*/
public class BillTypeEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
	/*
		boolean found = false;
		for (BillType d : BillType.values()) {
			try{
				if (d.ordinal() == Integer.parseInt(text)) {
					this.setValue(d);
					found = true;
					break;
				}
			}catch(Exception e){
				if (d.name().equals(text)) {
					this.setValue(d);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			// 错误的取值，我们默认为预算单类型，
			this.setValue(BillType.项目预算单);
		}*/
	}
}
