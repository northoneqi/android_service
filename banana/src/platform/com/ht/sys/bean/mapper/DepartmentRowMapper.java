package com.ht.sys.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ht.sys.bean.Department;

/** 
 * 类功能说明: 用于SpringJDBC对数据库中数据与实体关系映射
 * <p>Title: DepartmentRowMapper.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-24 下午05:04:38
 * @version V1.0
 */

public class DepartmentRowMapper implements RowMapper<Department> {

	public Department mapRow(ResultSet set, int index) throws SQLException {
		Department dept = new Department();
		dept.setId(set.getString("id"));
		dept.setCode(set.getString("code"));
		dept.setName(set.getString("name"));
		return dept;
	}

}
