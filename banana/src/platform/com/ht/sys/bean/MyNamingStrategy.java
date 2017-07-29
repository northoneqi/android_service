package com.ht.sys.bean;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;


/**
* 类功能说明: 自定义生成表和字段策略，防止和数据库关键字冲突
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-10-29 下午10:06:29
* @version V1.0
*/
public class MyNamingStrategy extends ImprovedNamingStrategy implements NamingStrategy {

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	
	private static final long serialVersionUID = -8228181764377758420L;

	/**
	 * <p>让数据库中的表字段自动加上前缀"f_",防止和数据库关键字冲突</p>
	 * */
	@Override
	public String propertyToColumnName(String propertyName) {
		
		//return "f_"+super.propertyToColumnName(propertyName);
		return super.propertyToColumnName(propertyName);
	}
	
	
	
}
