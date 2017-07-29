/**
* @Title: SysRoleService.java 
* @Package com.bluefat.sys.service.impl 
* @Description: 角色维护
* @author qj  
* @date 2013-8-23 下午2:16:38 
* @version V1.0
*/
package com.ht.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysResource;
import com.ht.sys.bean.SysRole;
import com.ht.sys.dao.impl.RoleResourceDao;
import com.ht.sys.dao.impl.SysRoleDao;

/**
* <p>类功能说明:角色服务类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:55:11
* @version V1.0
*/
@Service("sys_roleService")
public class SysRoleService extends SysService<SysRole>{

	private SysRoleDao sysRoleDao;
	private RoleResourceDao roleResourceDao;

	/**
	 * <p>得到该角色的菜单信息</p>
	 * @author qj  
	 * @date 2013-8-27 下午2:44:36 
	 * @param roleCode  角色编号
	 * @return
	 */
	public List<SysResource> getMenu(String roleCode,String fcode) {
		return roleResourceDao.getMenu(roleCode,fcode);
	}
	/**
	 * <p>得到该角色的顶级菜单信息</p>
	 * @author qj  
	 * @date 2013-8-27 下午2:44:36 
	 * @param roleCode  角色编号
	 * @return
	 */
	public List<SysResource> getNodeMenu(String roleCode) {
		return roleResourceDao.getNodeMenu(roleCode);
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
		return roleResourceDao.getButton(roleCode, parentCode);
	}
	
	/**
	 * <p>查询角色的功能权限</P>
	 * @author qj  
	 * @date 2013-8-27 下午5:14:43 
	 * @param roleCode    角色编号
	 * @param parentCode  资源的父级编号
	 * @return
	 */
	public List<SysResource> getAllResource(String roleCode, String parentCode){
		return roleResourceDao.getAllResource(roleCode, parentCode);
	}
	
	
	public SysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	@Autowired
	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		super.setSysDao(sysRoleDao);
		this.sysRoleDao = sysRoleDao;
	}

	public RoleResourceDao getRoleResourceDao() {
		return roleResourceDao;
	}

	@Autowired
	public void setRoleResourceDao(RoleResourceDao roleResourceDao) {
		this.roleResourceDao = roleResourceDao;
	}
	
	
	
}
