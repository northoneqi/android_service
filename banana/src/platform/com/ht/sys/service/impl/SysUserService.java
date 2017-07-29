package com.ht.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysUser;
import com.ht.sys.dao.impl.SysUserDao;
import com.ht.sys.util.db.QueryUtil;

/** 
 * <p>类功能说明: 用户管理服务类</p>
 * <p>Title: SysUserService.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-4 下午02:39:19
 * @version V1.0
 */
@Service("sys_userService")
public class SysUserService extends BaseServiceImpl<SysUser>{

	private SysUserDao userDao;

	/**
	 * <p>通过用户号查找用户</p>
	 * @author qj  
	 * @date 2013-8-4 下午02:25:34 
	 * @param username  用户账号
	 * @return 如果没有找到，则返回null
	 */
	public SysUser findByCode(String code){
		QueryUtil queryUtil = new QueryUtil();
		queryUtil.put("code", "=", code);
		
		List<SysUser> list = super.findByCondition(queryUtil);
		
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	public SysUserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(SysUserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	
	
}
