/**
* @Title: SqlBackupService.java 
* @Package com.bluefat.sys.service.impl 
* @Description: 数据库备份与还原 
* @author qj  
* @date 2013-8-20 下午9:39:59 
* @version V1.0
*/
package com.ht.sys.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.sys.bean.SqlBackup;
import com.ht.sys.dao.impl.SqlBackupDao;
import com.ht.sys.util.db.SqlBackUpHelper;

/**
* <p>类功能说明:数据库还原备份类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:51:19
* @version V1.0
*/
@Service("sys_sqlBackupService")
public class SqlBackupService extends SysService<SqlBackup>{

	private SqlBackupDao sqlBackupDao;

	/**
	 * <p>备份数据库</p>
	 * @author qj  
	 * @date 2013-8-22 下午3:47:15 
	 * @param path
	 */
	@Transactional
	public void backupDB(){
		SqlBackUpHelper backUp = new SqlBackUpHelper();
		SqlBackup backup = new SqlBackup();
		backup.setCode(sqlBackupDao.getCode("code"));
		backup.setName("自动备份");
		
		if(backUp.getDatabaseType().equals("sqlServer")){
			String backupPath = new File(SqlBackupService.class.getClassLoader().getResource("").getPath()+"/").getParentFile().getPath()+"/dbback/";
			String path = backupPath+"_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+ ".bak";
			
			String sql = "backup database "+backUp.getDatabase()+" to disk='"+path+"';";
			System.out.println(sql);
		}else if(backUp.getDatabaseType().equals("mysql")){
			String[] backPath = backUp.backUp();
			backup.setFileName(backPath[0]);
		}else {
			throw new RuntimeException("暂时不支持此数据的备份");
		}
		
	
		
		sqlBackupDao.save(backup);
		sqlBackupDao.clear();
	}
	public void sqlCallBack(String hql){
		sqlBackupDao.sqlCallBack(hql);
	}
	public boolean BackBpDataForSQL(String[] path){
		return sqlBackupDao.BackBpDataForSQL(path);
	}
	@Autowired
	public void setSqlBackupDao(SqlBackupDao sqlBackupDao) {
		super.setSysDao(sqlBackupDao);
		this.sqlBackupDao = sqlBackupDao;
	}
	
}
