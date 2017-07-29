/**
* @Title: RoleResourceDao.java 
* @Package com.bluefat.sys.dao.impl 
* @Description: TODO 
* @author qj  
* @date 2013-8-27 下午2:32:06 
* @version V1.0
*/
package com.ht.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ht.sys.bean.MRoleResource;
import com.ht.sys.bean.RoleResource;
import com.ht.sys.bean.SysResource;
import com.ht.sys.bean.mapper.RoleRosourceMapper;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明:角色资源管理类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:32:32
* @version V1.0
*/
@Repository("sys_roleResourceDao")
public class RoleResourceDao extends SysDao<RoleResource>{

	/**
	 * <p>得到所有启用的资源</p>
	 * @author qj
	 * @date 2013-11-15 上午10:33:09
	 * @param @return    
	 * @return List<MRoleResource>   
	 * @throws
	 */
	public List<MRoleResource> getAllResource(){
		String sql = "SELECT a.f_code as resourceCode, a.f_name as resourceName, " +
				" a.f_url as url, b.f_role_code as roleCode, b.f_role_name as roleName " +
				" FROM sys_resource a, sys_role_resource b "+
				" WHERE a.f_code = b.f_resource_code and a.f_state = '启用' and " +
				" a.f_url is not null and a.f_url != '' ";
		
		List<MRoleResource> list = this.jdbcTemplate.query(sql, new RoleRosourceMapper());
		
		return list;
	}
	
	/**
	 * <p>判断角色是否有该资源</p>
	 * @author qj  
	 * @date 2013-8-27 下午2:38:08 
	 * @param roleCode   角色编号
	 * @param resourceCode  资源编号
	 * @return   如果有返回true，否则返回false
	 */
	public boolean findByUserAndResource(String roleCode, String resourceCode){
		boolean boo = false;
		QueryUtil queryUtil = new QueryUtil();
		queryUtil.put("roleCode", "=", roleCode);
		queryUtil.put("resourceCode", "=", resourceCode);
		List<RoleResource> list = super.findByCondition(queryUtil);
		if(list.size() > 0){
			boo = true;
		}
		return boo;
	}
	
	/**
	 * <p>得到该角色的菜单信息</p>
	 * @author qj  
	 * @date 2013-8-27 下午2:44:36 
	 * @param roleCode  角色编号
	 * @return List<SysResource>
	 */
	@SuppressWarnings("unchecked")
	public List<SysResource> getMenu(String roleCode,String fcode){
		List<SysResource> list = new ArrayList<SysResource>();
		String hql = "from SysResource where code in(" +
				"select resourceCode from RoleResource where roleCode = ?) and type !='button' and state='启用' and  code like ?" +
				" order by code asc";
		org.hibernate.Query query = super.getSession().createQuery(hql);
		query.setParameter(0, roleCode);
		query.setParameter(1, fcode+"%");
		list = (List<SysResource>)query.list();
		return list;
	}
	/**
	 * <p>得到该角色的顶级菜单信息</p>
	 * @author qj  
	 * @date 2013-8-27 下午2:44:36 
	 * @param roleCode  角色编号
	 * @return List<SysResource>
	 */
	@SuppressWarnings("unchecked")
	public List<SysResource> getNodeMenu(String roleCode){
		List<SysResource> list = new ArrayList<SysResource>();
		String hql = "from SysResource where code in(" +
				"select resourceCode from RoleResource where roleCode = ?) and type ='accordion' and state='启用' " +
				" order by code desc";
		org.hibernate.Query query = super.getSession().createQuery(hql);
		query.setParameter(0, roleCode);
		list = (List<SysResource>)query.list();
		return list;
	}
	/**
	 * <p>得到角色的按钮信息</p>
	 * @author qj
	 * @date 2013-12-6 上午09:47:32
	 * @param roleCode   角色编号
	 * @param parentCode 按钮的上级编号
	 * @return List<SysResource>   返回得到的按钮
	 * @throws
	 */
	public List<SysResource> getButton(String roleCode, String parentCode){
		List<SysResource> list = new ArrayList<SysResource>();
		String hql = "from SysResource where code in(" +
				"select resourceCode from RoleResource where roleCode = ?) and type ='button' and state='启用' and parentCode=?" +
				" order by code asc";
		org.hibernate.Query query = super.getSession().createQuery(hql);
		query.setParameter(0, roleCode);
		query.setParameter(1, parentCode);
		
		list = (List<SysResource>)query.list();
		return list;
	}
	
	/**
	 * <p>查询角色的功能权限</p>
	 * @author qj  
	 * @date 2013-8-27 下午5:14:43 
	 * @param roleCode    角色编号
	 * @param parentCode  资源的父级编号
	 * @return List<SysResource>
	 */
	@SuppressWarnings("unchecked")
	public List<SysResource> getAllResource(String roleCode, String parentCode){
		List<SysResource> list = new ArrayList<SysResource>();
		String hql = "from SysResource o where o.parentCode = ? and o.state='启用' order by o.code desc";
		org.hibernate.Query queryResource = super.getSession().createQuery(hql);
		queryResource.setParameter(0, parentCode);
		list = (List<SysResource>)queryResource.list();
		
		String hql2 = "from RoleResource where roleCode = ? and parentResourceCode = ?";
		org.hibernate.Query queryRoleResource = super.getSession().createQuery(hql2);
		queryRoleResource.setParameter(0, roleCode);
		queryRoleResource.setParameter(1, parentCode);
		List<RoleResource> listRoleResource = (List<RoleResource>)queryRoleResource.list();
		
		//将用户有的功能全选设置checked=true
		for(SysResource resource: list){
			for(RoleResource roleResource: listRoleResource){
				if(resource.getCode().equals(roleResource.getResourceCode())){
					resource.setChecked(true);
					break;
				}
			}
		}
		return list;
	}
}
