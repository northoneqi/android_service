package com.ht.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.Staff;
import com.ht.sys.bean.SysUser;
import com.ht.sys.bean.Tree;
import com.ht.sys.util.db.QueryUtil;

/** 
 * <p>类功能说明: 用户数据库访问类</p>
 * <p>Title:Staff.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-4 下午02:20:25
 * @version V1.0
 */
@Repository("staffDao")
public class StaffDao extends BaseDaoImpl<Staff>{
	
	
	/**
	 * <p>通过用户号查找用户</p>
	 * @author qj  
	 * @date 2013-8-4 下午02:25:34 
	 * @param username  用户账号
	 * @return 如果没有找到，则返回null
	 */
	public Staff findByName(String username){
		QueryUtil queryUtil = new QueryUtil();
		queryUtil.put("userName", "=", username);
		List<Staff> list = super.findByCondition(queryUtil);
		
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	  
}
