/**
* @Package com.bluefat.sys.dao
* @Description: 数据权限
* @author qj  
* @date 2013-12-27 20:47:48
* @version V1.0
*/
package com.ht.sys.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ht.sys.bean.RoleDataAuth;
import com.ht.sys.dao.impl.BaseDaoImpl;

/**
* 类功能说明: 数据权限
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>该代码由系统自动产生，请按需求修改</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-12-27 20:47:48
* @version V1.0
*/

@Repository("sys_RoleDataAuthDao")
public class RoleDataAuthDao extends BaseDaoImpl<RoleDataAuth>{
	
	/**
	 * <p>根据单据id删除明细</p>
	 * @author qj
	 * @date 2013-11-18 上午10:18:59
	 * @param documentNum    单据编号
	 * @return void   
	 * @throws
	 */
	public void deleteByDocumentId(String documentId){
		String hql = "delete RoleDataAuth where document.id = ?";
		Session session = super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, documentId);
		query.executeUpdate();
	}
}
