/**
* @Title: SqlBackupDao.java 
* @Package com.bluefat.sys.dao.impl 
* @Description: 数据库备份与还原
* @author qj  
* @date 2013-8-20 下午9:37:54 
* @version V1.0
*/
package com.ht.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.ht.sys.bean.SqlBackup;


/**
* <p>类功能说明:数据库管理类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:34:08
* @version V1.0
*/
@Repository("sys_sqlBackupDao")
public class SqlBackupDao extends SysDao<SqlBackup>{
	public boolean BackBpDataForSQL(String[] path){
		boolean res= true;
		if(path[0].endsWith(".bak")){
			String filePath="backup database "+path[1]+" to disk='"+path[2]+path[0]+"' with init";
			res=(getSession().createSQLQuery(filePath).executeUpdate()==0);
		}
		return res;
	}
	public void sqlCallBack(String hql){
		getSession().createSQLQuery(hql).executeUpdate();
	}
}
