package com.ht.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ht.sys.bean.Department;
import com.ht.sys.bean.Tree;
import com.ht.sys.util.db.QueryUtil;

/** 
 * 类功能说明: 部门数据库操作实现类
 * <p>Title: DepartmentDaoImpl.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-25 下午09:35:17
 * @version V1.0
 */

@Repository("sys_departmentDao")
public class DepartmentDao extends BaseDaoImpl<Department>{

	/**
	 * <p>得到单位部门树</p>
	 * @author qj  
	 * @date 2013-8-13 上午10:45:35 
	 * @param queryUtil  查询条件
	 * @return  返回部门树
	 */
	public Tree getTree(QueryUtil queryUtil){
		Tree tree = new Tree();
		
		//查找第一个父节点
		List<Department> list = super.findByCondition(queryUtil);
		if(list.size() == 0){
			return tree;
		}
		tree = deptToTree(list.get(0));
		
		findChildren(tree, queryUtil);
		return tree;
	}
	
	/**
	 * <p>根据父节点查找子节点</p>
	 * @author qj  
	 * @date 2013-8-13 下午9:08:15 
	 * @param tree   父节点
	 */
	private void findChildren(Tree tree,  QueryUtil queryUtil){
		//在此需要查找一次第一个父节点的直接子节点
		QueryUtil childQueryUtil = new QueryUtil();
		childQueryUtil.setGroupby(queryUtil.getGroupby());
		childQueryUtil.setOrderby(queryUtil.getOrderby());
		childQueryUtil.setWhereHql(queryUtil.getWhereHql());
		childQueryUtil.setParames(queryUtil.getArrayParames());
		childQueryUtil.getArrayParames().remove(0);
		childQueryUtil.getArrayParames().add(0, tree.getCode());
		
		List<Department> childeList = super.findByCondition(childQueryUtil);
		
		if(childeList.size() == 0){
			tree.setLeaf(true);
			return;
		}
		
		for(int i = 0; i < childeList.size(); i++){
			Tree node = deptToTree(childeList.get(i));
			tree.getChildren().add(node);
			
			findChildren(node, queryUtil);
		}
		
	}
	
	/**
	 * <p>将单位部门转换为树</p>
	 * @author qj  
	 * @date 2013-8-13 上午09:20:36 
	 * @param dept
	 * @return
	 */
	private Tree deptToTree(Department dept){
		Tree tree = new Tree();
		tree.setId(dept.getId());
		tree.setCode(dept.getCode());
		tree.setText(dept.getName());
		return tree;
	}
	public Department getParentDepartment(){
		return (Department)this.getSession().createQuery("SELECT o FROM Department o where o.united=1").list().get(0);
	}
	public List<Object[]> getTreeList(String node){
		return this.getSession().createSQLQuery("SELECT b.code,b.name,(SELECT COUNT(*) FROM home_employeer WHERE departmentCode=b.code) rs,(SELECT COUNT(*) FROM sys_department WHERE unitCode=b.code) bms FROM sys_department b where b.unitCode=?").setParameter(0, node).list();
	}
}
