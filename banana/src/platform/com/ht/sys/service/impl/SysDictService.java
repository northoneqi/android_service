/**
* @Title: SysDictService.java 
* @Package com.bluefat.sys.service.impl 
* @Description: 数据字典
* @author qj  
* @date 2013-8-18 下午5:52:21 
* @version V1.0
*/
package com.ht.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysDict;
import com.ht.sys.dao.impl.SysDictDao;

/**
* <p>类功能说明: 数据字典服务类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:52:21
* @version V1.0
*/
@Service("sys_dictService")
public class SysDictService extends SysService<SysDict>{
	
	private SysDictDao sysDictDao;

	@Autowired
	public void setSysDictDao(SysDictDao sysDictDao) {
		super.setSysDao(sysDictDao);
		this.sysDictDao = sysDictDao;
	}

	public SysDictDao getSysDictDao() {
		return sysDictDao;
	}
	
	
}
