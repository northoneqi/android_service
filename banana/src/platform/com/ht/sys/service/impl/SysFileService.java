/**
* @Title: SysFileService.java 
* @Package com.bluefat.sys.service.impl 
* @Description: 附件 
* @author qj  
* @date 2013-9-11 上午11:38:20 
* @version V1.0
*/
package com.ht.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysFile;
import com.ht.sys.dao.impl.SysFileDao;

/**
* <p>类功能说明:文件服务类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:52:45
* @version V1.0
*/
@Service("sys_fileService")
public class SysFileService extends SysService<SysFile>{

	private SysFileDao fileDao;

	public SysFileDao getFileDao() {
		return fileDao;
	}

	@Autowired
	public void setFileDao(SysFileDao fileDao) {
		this.setSysDao(fileDao);
		this.fileDao = fileDao;
	}
	
	
	/**
	 * <p>按文件分组删除</p>
	 * @param fileCode  文件分组编号
	 */
	public void deleteByCode(String fileCode){
		fileDao.deleteByCode(fileCode);
	}
	
}
