package com.ht.sys.util;

import java.lang.reflect.Field;
import java.util.Date;

import com.ht.sys.util.db.QueryUtil;


/**
 * 查询条件自动组装
 * @author niurenhua(niuren_hua@sina.com)
 * @date 2014-11-06
 */
public class QueryConditionUtils {
	
	public static QueryUtil queryCondition(Object c,QueryUtil queryUtil){
		try {
			Field fields[] = c.getClass().getDeclaredFields();//FormBean
			Field field = null;
			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				field.setAccessible(true);// 修改访问权限
				if(!"serialVersionUID".equals(field.getName())&&!"id".equals(field.getName())){
					if(field.getType() == Date.class && field.get(c) != null){
						queryUtil.put(field.getName(), "<=", field.get(c));
					}else if(field.getType() == Float.class && field.get(c) != null){
						queryUtil.put(field.getName(), "=", field.get(c));
					}else if(field.getType() == Double.class && field.get(c) != null){
						queryUtil.put(field.getName(), "=", field.get(c));
					}else if(field.getType() == Integer.class && field.get(c) != null){
						queryUtil.put(field.getName(), "=", field.get(c));
					}else{
						if(field.get(c) != null)
							queryUtil.put(field.getName(), "like", "%"+field.get(c)+"%");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryUtil;
	}
}
