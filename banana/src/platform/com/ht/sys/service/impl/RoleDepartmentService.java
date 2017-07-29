package com.ht.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.RoleDepartment;
import com.ht.sys.bean.SysRole;
import com.ht.sys.dao.impl.RoleDepartmentDao;

/**
* <p>类功能说明: 角色部门服务类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-10-30 上午10:42:11
* @version V1.0
*/
@Service("sys_RoleDepartmentService")
public class RoleDepartmentService extends BaseServiceImpl<RoleDepartment>{

	private RoleDepartmentDao roleDepartmentDao;

	public RoleDepartmentDao getRoleDepartmentDao() {
		return roleDepartmentDao;
	}

	@Autowired
	public void setRoleDepartmentDao(RoleDepartmentDao roleDepartmentDao) {
		super.setBaseDao(roleDepartmentDao);
		this.roleDepartmentDao = roleDepartmentDao;
	}
	
	/**
	 * <p>给角色分配功能权限</p>
	 * @author qj
	 * @date 2013-10-30 上午11:31:10
	 * @param @param role   角色
	 * @param @param list   部门 
	 * @return void   
	 * @throws
	 */
	public void allotDataAuth(SysRole role, List<RoleDepartment> list){
		roleDepartmentDao.delete("delete from sys_role_department where f_role_code = '"+role.getCode()+"'");
		this.saveList(list);
	}
	
	/**
	 * <p>查询角色的数据权限</p>
	 * @author qj  
	 * @date 2013-8-27 下午5:14:43 
	 * @param roleCode    角色编号
	 * @return List<SysResource>
	 */
	public List<Department> getRoleDepartment(String roleCode, String parentCode){
		return roleDepartmentDao.getRoleDepartment(roleCode, parentCode);
	}
}
