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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.RoleDepartment;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明:角色部门管理类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:32:32
* @version V1.0
*/
@Repository("sys_roleDepartmentDao")
public class RoleDepartmentDao extends SysDao<RoleDepartment>{
	private DepartmentDao departmentDao;
	/**
	 * <p>查询角色的数据权限</p>
	 * @author qj  
	 * @date 2013-8-27 下午5:14:43 
	 * @param roleCode    角色编号
	 * @return List<SysResource>
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getRoleDepartment(String roleCode, String parentCode){
		if(parentCode == null || parentCode.equals("")){
			parentCode = "root";
		}
		List<Department> list = new ArrayList<Department>();
		String hql = "from Department o where o.state='启用' and o.unitCode=? order by o.code desc";
		org.hibernate.Query queryDepartment = super.getSession().createQuery(hql);
		queryDepartment.setParameter(0, parentCode);
		list = (List<Department>)queryDepartment.list();
		
		String hql2 = "from RoleDepartment where roleCode = ?";
		org.hibernate.Query queryRoleDepartment = super.getSession().createQuery(hql2);
		queryRoleDepartment.setParameter(0, roleCode);
		List<RoleDepartment> listRoleDepartment = (List<RoleDepartment>)queryRoleDepartment.list();
		
		//将用户有的功能全选设置checked=true
		for(Department department: list){
			for(RoleDepartment roleDepartment: listRoleDepartment){
				if(department.getCode().equals(roleDepartment.getDepartmentCode())){
					department.setChecked(true);
					break;
				}
			}
		}
		
		//查询是否有子部门
		for(Department department: list){
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("unitCode", "=", department.getCode());
			List<Department> children = departmentDao.findByCondition(queryUtil);
			if(children.size() > 0) {
				department.setLeaf(false);
			}else{
				department.setLeaf(true);
			}
		}
		return list;
	}
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	
}
