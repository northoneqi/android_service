package com.ht.sys.service.impl;

import com.ht.sys.dao.impl.SysDao;

/** 
 * <p>类功能说明: 系统服务类的父类</p>
 * <p>Title: SysService.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-12 下午10:09:39
 * @version V1.0
 */

public class SysService<T> extends BaseServiceImpl<T>{
	protected SysDao<T> sysDao;
	
	public void setSysDao(SysDao<T> sysDao) {
		super.setBaseDao(sysDao);
		this.sysDao = sysDao;
	}
	
	public SysDao<T> getSysDao() {
		return sysDao;
	}

	/**
	 * <p>得到当前的code</p>
	 * @author qj  
	 * @date 2013-8-12 下午10:15:32 
	 * @param columnName  列名
	 * @return 返回最大的code
	 */
	public String getCode(String columnName){
		return this.sysDao.getCode(columnName);
	}
}
