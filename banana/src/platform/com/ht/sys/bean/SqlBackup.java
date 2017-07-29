/**
* @Title: SqlBackup.java 
* @Package com.bluefat.sys.bean 
* @Description: 数据库备份
* @author qj  
* @date 2013-8-20 下午9:35:15 
* @version V1.0
*/
package com.ht.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 类功能说明: 数据库备份还原记录
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午09:16:46
* @version V1.0
*/
@Entity
@Table(name="sys_sql_backup")
public class SqlBackup extends SysEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4580303199788670518L;

	/**备份文件名称*/
	private String fileName;

	@Column(name="f_file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
