package com.ht.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysLog;
import com.ht.sys.dao.impl.SysLogDao;

/** 
 * <p>类功能说明: 系统操作日志服务类</p>
 * <p>Title: SysLogService.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午08:57:42
 * @version V1.0
 */
@Service("sys_logService")
public class SysLogService extends SysService<SysLog>{
	private SysLogDao sysLogDao;

	@Autowired
	public void setSysLogDao(SysLogDao sysLogDao) {
		super.setSysDao(sysLogDao);
		this.sysLogDao = sysLogDao;
	}

	public SysLogDao getSysLogDao() {
		return sysLogDao;
	}
	
	
}
