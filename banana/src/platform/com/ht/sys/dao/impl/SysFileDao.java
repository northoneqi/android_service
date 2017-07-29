/**
* @Title: FileDao.java 
* @Package com.bluefat.sys.dao.impl 
* @Description: 附件
* @author qj  
* @date 2013-9-11 上午11:37:03 
* @version V1.0
*/
package com.ht.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.ht.sys.bean.SysFile;

/**
* <p>类功能说明:文件管理类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:37:49
* @version V1.0
*/
@Repository("sys_fileDao")
public class SysFileDao extends SysDao<SysFile>{

	/**
	 * <p>按文件分组删除</p>
	 * @param fileCode  文件分组编号
	 */
	public void deleteByCode(String fileCode){
		jdbcTemplate.update("delete from sys_fileDao where fileCode=?", fileCode);
	}
}
