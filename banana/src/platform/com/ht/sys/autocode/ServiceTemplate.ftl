/**
* @Package com.bluefat.${app}.service
* @Description: ${description}
* @author qj  
* @date ${.now} 
* @version V1.0
*/
package com.bluefat.${app}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluefat.${app}.bean.${entity};
import com.bluefat.budget.dao.${entity}Dao;
import com.bluefat.sys.service.impl.BaseServiceImpl;

/**
* 类功能说明: ${description}
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>该代码由系统自动产生，请按需求修改</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date ${.now}
* @version V1.0
*/

@Service("${app}_${entity}Service")
public class ${entity}Service extends BaseServiceImpl<${entity}>{

	private ${entity}Dao <@lowerFC>${entity}</@lowerFC>Dao;
	
	public ${entity}Dao get${entity}Dao() {
		return <@lowerFC>${entity}</@lowerFC>Dao;
	}

	@Autowired
	public void set${entity}Dao(${entity}Dao <@lowerFC>${entity}</@lowerFC>Dao) {
		super.setBaseDao(<@lowerFC>${entity}</@lowerFC>Dao);
		this.<@lowerFC>${entity}</@lowerFC>Dao = <@lowerFC>${entity}</@lowerFC>Dao;
	}
}
