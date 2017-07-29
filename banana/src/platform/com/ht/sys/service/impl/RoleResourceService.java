package com.ht.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.RoleResource;
import com.ht.sys.bean.SysRole;
import com.ht.sys.dao.impl.RoleResourceDao;

/**
* <p>类功能说明: 资源角色服务类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-10-30 上午10:42:11
* @version V1.0
*/
@Service("sys_RoleResourceService")
public class RoleResourceService extends BaseServiceImpl<RoleResource>{

	private RoleResourceDao roleResourceDao;

	public RoleResourceDao getRoleResourceDao() {
		return roleResourceDao;
	}

	@Autowired
	public void setRoleResourceDao(RoleResourceDao roleResourceDao) {
		super.setBaseDao(roleResourceDao);
		this.roleResourceDao = roleResourceDao;
	}
	
	/**
	 * <p>给角色分配功能权限</p>
	 * @author qj
	 * @date 2013-10-30 上午11:31:10
	 * @param @param role   角色
	 * @param @param list   资源 
	 * @return void   
	 * @throws
	 */
	public void allotFunctionAuth(SysRole role, List<RoleResource> list){
		roleResourceDao.delete("delete from sys_role_resource where f_role_code = '"+role.getCode()+"'");
		this.saveList(list);
	}
}
