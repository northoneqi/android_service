package com.ht.sys.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ht.sys.bean.MRoleResource;

public class RoleRosourceMapper implements RowMapper<MRoleResource>{

	@Override
	public MRoleResource mapRow(ResultSet rs, int index) throws SQLException {
		
		MRoleResource roleRes = new MRoleResource();
		roleRes.setRoleCode(rs.getString("roleCode"));
		roleRes.setRoleName(rs.getString("roleName"));
		roleRes.setResourceCode(rs.getString("resourceCode"));
		roleRes.setResourceName(rs.getString("resourceName"));
		roleRes.setUrl(rs.getString("url"));
		
		return roleRes;
	}

}
