package com.ht.sys.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.SysUser;
import com.ht.sys.bean.Tree;
import com.ht.sys.dao.impl.DepartmentDao;
import com.ht.sys.dao.impl.SysUserDao;
import com.ht.sys.util.db.QueryUtil;

/** 
 * <p>类功能说明: 部门操作服务实现类</p>
 * <p>Title: BaseServiceImpl.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-25 下午09:27:52
 * @version V1.0
 */


@Service("sys_departmentService")
public class DepartmentService extends BaseServiceImpl<Department> {
	private DepartmentDao departmentDao;
	private SysUserDao userDao;

	/**
	 * <p>得到单位部门树</p>
	 * @author qj  
	 * @date 2013-8-13 上午10:45:35 
	 * @param queryUtil  查询条件
	 * @return  返回部门树
	 */
	public Tree getTree(QueryUtil queryUtil){
		return departmentDao.getTree(queryUtil);
	}
	
	
	/**
	 * <p>得到部门树</p>
	 * @author qj
	 * @date 2013-11-15 上午10:50:34
	 * @param @param node
	 * @param @return    
	 * @return List<Tree>   
	 * @throws
	 */
	public List<Tree> getTreeList(String node){
		List<Tree> treeList=new ArrayList<Tree>();
		List<Object[]> olist=departmentDao.getTreeList(node);
		Tree tree=null;
		if(olist!=null){
			for(Object[] o:olist){
				tree = new Tree();
				tree.setCode(o[0].toString());
				tree.setText(o[1].toString());
				tree.setId("D"+o[0].toString());
				if(o[2].toString().equals("0")&&o[3].toString().equals("0")){
					tree.setLeaf(true);
				}else{
					tree.setLeaf(false);
				}
				treeList.add(tree);
			}
		}
		return treeList;
	}
	
	public void deleteDepartment(String[] ids){
		for(String id: ids){
			Department department = departmentDao.get(id);
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("unitCode", "=", department.getCode());
			List<Department> list = departmentDao.findByCondition(queryUtil);
			if(list.size() == 0){
				departmentDao.delete((Serializable)id);
			}else{
				throw new RuntimeException("【"+department.getName()+"】存在子部门，不能删除");
			}
			
			QueryUtil queryUtil2 = new QueryUtil();
			queryUtil2.put("departmentCode", "=", department.getCode());
			List<SysUser> users = userDao.findByCondition(queryUtil2);
			if(users.size() > 0) {
				throw new RuntimeException("【"+department.getName()+"】存在用户，不能删除");
			}
		}
	}
	
	
	public Department getParentDepartment(){
		return departmentDao.getParentDepartment();
	}
	
	@Autowired
	public void setDepartmentDao(DepartmentDao departmentDao) {
		super.setBaseDao(departmentDao);
		this.departmentDao = departmentDao;
	}


	public SysUserDao getUserDao() {
		return userDao;
	}


	@Autowired
	public void setUserDao(SysUserDao userDao) {
		this.userDao = userDao;
	}
	
}
