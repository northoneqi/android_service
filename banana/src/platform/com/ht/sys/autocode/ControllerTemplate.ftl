/**
* @Package com.bluefat.${app}.controller
* @Description: ${description}
* @author qj  
* @date ${.now} 
* @version V1.0
*/
package com.bluefat.${app}.controller;

import java.io.Serializable;

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
	 * @Description: ${description}管理界面
	 * @author qj  
	 * @date ${.now}
	 * @return
	 */
	@RequestMapping("listPage")
	public String listPage(){
		return "${app}/<@lowerFC>${entity}</@lowerFC>_list";
	}
	
	
	/**
	 * @Description: ${description}查询 
	 * @author qj  
	 * @date ${.now}
	 * @param request
	 * @param response
	 */
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			Pager<${entity}> pager) {
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			<@lowerFC>${entity}</@lowerFC>Service.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
			
			super.printLog(request, "${description}查询");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "${description}查询", e);
			super.setGridJson(response, "${description}出现异常，请联系管理员");
		}
	}
	
	/**
	 * @Description: 保存${description}信息
	 * @author qj  
	 * @date ${.now} 
	 * @param request
	 * @param response
	 * @param <@lowerFC>${entity}</@lowerFC>
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, ${entity} <@lowerFC>${entity}</@lowerFC>){
		try{
			<@lowerFC>${entity}</@lowerFC>Service.save(<@lowerFC>${entity}</@lowerFC>);
			super.printLog(request, "保存${description}信息");
			super.setAjaxMsg(response, true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "保存${description}信息", e);
			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * @Description: 根据id查找${description}信息
	 * @author qj  
	 * @date ${.now}
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request, HttpServletResponse response,
			String id){
		try{
			${entity} <@lowerFC>${entity}</@lowerFC> = <@lowerFC>${entity}</@lowerFC>Service.get(id);
			super.printLog(request, "根据id查找${description}信息");
			super.setExtJsFormJson(response, true, <@lowerFC>${entity}</@lowerFC>);
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找${description}信息", e);
			super.setExtJsFormJson(response, e.getMessage());
		}
	}
	
	
	/**
	 * @Description: 更新${description}信息
	 * @author qj  
	 * @date ${.now}
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, ${entity} <@lowerFC>${entity}</@lowerFC>){
		try{
			<@lowerFC>${entity}</@lowerFC>Service.update(<@lowerFC>${entity}</@lowerFC>);
			super.printLog(request, "更新${description}信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "更新${description}信息", e);
			super.setAjaxMsg(response, false, "更新失败! 可能原因：编号重复");
		}
	}
	
	
	/**
	 * @Description: 删除${description}信息 
	 * @author qj  
	 * @date ${.now}
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] ids){
		try{
			<@lowerFC>${entity}</@lowerFC>Service.delete((Serializable[])ids);
			super.printLog(request, "删除${description}信息");
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除${description}信息", e);
			super.setAjaxMsg(response, false, "删除失败！可能原因：该${description}正在被使用。");
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
