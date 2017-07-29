/**
* @Title: SysResourceDaao.java 
* @Package com.bluefat.sys.dao.impl 
* @Description: 资源管理
* @author qj  
* @date 2013-8-23 下午3:04:41 
* @version V1.0
*/
package com.ht.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ht.sys.bean.SysResource;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明:资源管理类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:38:43
* @version V1.0
*/
@Repository("sys_resourceDao")
public class SysResourceDao extends SysDao<SysResource>{

	/**
	 * <p>得到单位部门树</p>
	 * @author qj  
	 * @date 2013-8-13 上午10:45:35 
	 * @param queryUtil  查询条件
	 * @return  返回资源树
	 */
	public List<SysResource> getTree(QueryUtil queryUtil){
		//查找第一个父节点
		List<SysResource> list = super.findByCondition(queryUtil);
		for(SysResource resource: list){
			findChildren(resource, queryUtil);
		}
		return list;
	}
	public void deleteRoleResource(String resourceCode){
		String delete = "delete from sys_role_resource where f_resource_code = '"+resourceCode+"'";
		try {
			super.delete(delete);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * <p>根据父节点查找子节点</p>
	 * @author qj  
	 * @date 2013-8-13 下午9:08:15 
	 * @param tree   父节点
	 */
	private void findChildren(SysResource resource,  QueryUtil queryUtil){
		//在此需要查找一次第一个父节点的直接子节点
		QueryUtil childQueryUtil = new QueryUtil();
		childQueryUtil.setGroupby(queryUtil.getGroupby());
		childQueryUtil.setOrderby(queryUtil.getOrderby());
		childQueryUtil.setWhereHql(queryUtil.getWhereHql());
		childQueryUtil.setParames(queryUtil.getArrayParames());
		childQueryUtil.getArrayParames().remove(0);
		childQueryUtil.getArrayParames().add(0, resource.getCode());
		
		List<SysResource> childeList = super.findByCondition(childQueryUtil);
		
		if(childeList.size() == 0){
			resource.setLeaf(true);
		}
		
		if(resource.getType().equals("accordion")){
			resource.setTypeName("布局");
		}else if(resource.getType().equals("menu")){
			resource.setTypeName("菜单");
		}else if(resource.getType().equals("button")){
			resource.setTypeName("按钮");
		}
		
	}
}
