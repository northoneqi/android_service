package com.ht.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;

/** 
 * <p>类功能说明: 系统管理类</p>
 * <p>Title: SysDao.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-12 下午09:33:28
 * @version V1.0
 */

public class SysDao<T> extends BaseDaoImpl<T>{

	/**
	 * <p>得到该列的最大值</p>
	 * @author qj  
	 * @date 2013-8-12 下午10:06:09 
	 * @param columnName  列名
	 * @return  返回该列的最大值
	 */
	@SuppressWarnings("unchecked")
	public String getCode(String columnName){
		String code;
		//mysql的字符串转数字方法，不知道其他的数据库是否支持
		String hql = "SElECT MAX(o."+columnName+"+0) FROM "+super.entityClass.getName()+" o ";
		//System.out.println(hql);
		Query query = super.getSession().createQuery(hql);
		List<Object> list = (List<Object>)query.list();
		if (list.size() > 0) {
			if(list.get(0) == null){
				code = "1";
			}else{
				code = String.valueOf((Integer)list.get(0)+1);
			}
		}else{
			code = "1";
		}
		return code;
	}
}
