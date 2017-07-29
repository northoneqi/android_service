/**
* @Package com.bluefat.sys.service
* @Description: 系统基本信息
* @author qj  
* @date 2014-2-25 9:10:11 
* @version V1.0
*/
package com.ht.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysInfo;
import com.ht.sys.dao.SysInfoDao;

/**
* 类功能说明: 系统基本信息
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>该代码由系统自动产生，请按需求修改</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2014-2-25 9:10:11
* @version V1.0
*/

@Service("sys_SysInfoService")
public class SysInfoService extends BaseServiceImpl<SysInfo>{

	private SysInfoDao sysInfoDao;
	
	public SysInfoDao getSysInfoDao() {
		return sysInfoDao;
	}

	@Autowired
	public void setSysInfoDao(SysInfoDao sysInfoDao) {
		super.setBaseDao(sysInfoDao);
		this.sysInfoDao = sysInfoDao;
	}
}
