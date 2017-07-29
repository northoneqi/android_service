/**
* @Package com.bluefat.${app}.controller
* @Description: ${description}
* @author qj  
* @date ${.now} 
* @version V1.0
*/
package com.bluefat.${app}.controller;

import java.io.Serializable;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluefat.${app}.bean.${entity};
import com.bluefat.budget.service.${entity}Service;
import com.bluefat.sys.controller.BaseController;
import com.bluefat.sys.util.Pager;
import com.bluefat.sys.util.db.QueryUtil;

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

@Controller("${app}_${entity}Controller")
@RequestMapping("${app}/<@lowerFC>${entity}</@lowerFC>")
public class ${entity}Controller extends BaseController<${entity}>{

	private ${entity}Service <@lowerFC>${entity}</@lowerFC>Service;
	
	/**
	 * <p>得到单据的明细</p>
	 * @author qj
	 * @date 2013-11-26 上午09:22:06
	 * @param request
	 * @param response
	 * @param id    单据id
	 * @return void   
	 * @throws
	 */
	@RequestMapping("getDetails")
	public void getDetails(HttpServletRequest request, HttpServletResponse response,
			String id) {
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("document.id", "=", id);
			
			List<${entity}> list = <@lowerFC>${entity}</@lowerFC>Service.findByCondition(queryUtil);
			
			super.setGridJson(response, list.size(), list, new String[]{"document"});

			super.printLog(request, "${description}查询");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "${description}查询", e);
			super.setGridJson(response, "${description}出现异常，请联系管理员");
		}
	}
	
	public ${entity}Service get${entity}Service() {
		return <@lowerFC>${entity}</@lowerFC>Service;
	}

	@Autowired
	public void set${entity}Service(${entity}Service <@lowerFC>${entity}</@lowerFC>Service) {
		this.<@lowerFC>${entity}</@lowerFC>Service = <@lowerFC>${entity}</@lowerFC>Service;
	}
}
