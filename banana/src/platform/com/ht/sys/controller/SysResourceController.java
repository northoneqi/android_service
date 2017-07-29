/**
* @Title: SysResourceController.java 
* @Package com.bluefat.sys.controller 
* @Description: 资源管理
* @author qj  
* @date 2013-8-23 下午3:09:18 
* @version V1.0
*/
package com.ht.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.SysResource;
import com.ht.sys.bean.SysUser;
import com.ht.sys.core.function.Accordion;
import com.ht.sys.core.function.ParseXMLFunction;
import com.ht.sys.service.impl.SysResourceService;
import com.ht.sys.util.RequestUtil;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明: 资源管理</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午09:44:13
* @version V1.0
*/
@Controller("sys_resourceController")
@RequestMapping("sys/resource")
public class SysResourceController extends SysController<SysResource>{

	private SysResourceService resourceService;
	
	/**
	 * <p>资源管理页面</p>
	 * @author qj  
	 * @date 2013-8-23 下午3:12:55 
	 * @return
	 */
	@RequestMapping("listPage")
	public String roleListPage(){
		return "sys/resource_list";
	}
	
	/**
	 * <p>查询资源列表</p>
	 * @author qj  
	 * @date 2013-8-23 下午9:04:34 
	 * @param request
	 * @param response
	 * @param pager
	 */
	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request, HttpServletResponse response,
			String parentCode){
		RequestUtil.printAttritbute(request);
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("code", "desc");
			
			if(!isEmpty(parentCode)){
				queryUtil.put("parentCode", "=", parentCode);
			}else {
				queryUtil.put("parentCode", "=", "root");
			}
			List<SysResource> list = resourceService.getTree(queryUtil);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[]{"icon"});
			super.setAjaxMsg(response, JSONArray.fromObject(list,jsonConfig).toString());
			super.printLog(request, "查询资源信息");
		}catch(Exception e){
			e.printStackTrace();
			super.setGridJson(response, "系统繁忙，请稍后再试");
			super.printLog(request, "查询资源信息", e);
		}
	}

	
	/**
	 * <p>初始化资源信息</p>
	 * @author qj  
	 * @date 2013-8-23 下午9:05:20 
	 * @param request
	 * @param response
	 */
	@RequestMapping("init")
	public void initResource(HttpServletRequest request, HttpServletResponse response){
		try{
			ParseXMLFunction parser = new ParseXMLFunction();
			String path = request.getSession().getServletContext().getRealPath("/");
			parser.load(path);
			List<Accordion> list = parser.getFunctionList();
			System.out.println(JSONArray.fromObject(list));
			resourceService.saveAccordion(list);
			super.setAjaxMsg(response, true, "初始化成功！");
			super.printLog(request, "初始化资源信息");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "初始化资源信息", e);
			super.setAjaxMsg(response, false, "初始化失败！");
		}
	}
	
	/**
	 * 增加资源
	 * @param request
	 * @param response
	 * @param resource
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response,SysResource resource){
		if(resource!=null){
			try{
				if(resource.getParentCode()!=""){
					SysResource parent = resourceService.get(resource.getParentCode());
					SysUser user = (SysUser)request.getSession().getAttribute("loginUser");
					resource.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					resource.setRemark(user.getUserName()+"（"+user.getDepartmentName()+"）");
					resource.setParentCode(parent.getCode());
					resource.setType("menu");
					resourceService.save(resource);
				}
				
				this.setAjaxMsg(response, "{success:true,msg:'添加成功'}");
			}catch(Exception e){
				e.printStackTrace();
				this.setAjaxMsg(response, "{success:false,msg:'"+e.getLocalizedMessage()+"'}");
			}
		}else{
			this.setAjaxMsg(response, "{success:false,msg:'实例为空'}");
		}
		
	}
	/**
	 * 更新
	 * @date Tue Nov 18 14:06:09 CST 2014
	 * @author niurenhua(niuren_hua@sina.com)
	*/
	@RequestMapping("update")
	public void update(HttpServletRequest request,HttpServletResponse response,SysResource resource){
		if(resource!=null){
			resourceService.update(resource);
			this.setAjaxMsg(response, "{success:true,msg:'更新成功'}");
		}else{
			this.setAjaxMsg(response, "{success:false,msg:'实例为空'}");
		}
		
	}
	/**
	 * 加载表单
	 * @date Tue Nov 18 14:06:09 CST 2014
	 * @author niurenhua(niuren_hua@sina.com)
	*/
	@RequestMapping("load")
	public void load(HttpServletRequest request, HttpServletResponse response,SysResource resource){
		try{
			SysResource entity = resourceService.get(resource.getId());
			super.printLog(request, "根据id查找用户信息");
			super.setExtJsFormJson(response, true, entity);
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找用户信息", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}
	/**
	 * 删除
	 * @date Tue Nov 18 14:06:09 CST 2014
	 * @author niurenhua(niuren_hua@sina.com)
	*/
	@RequestMapping("delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,String ids){
		if(ids!=null&&ids.length()>0){
			SysResource resource = resourceService.load(ids);
			resourceService.deleteRoleResource(resource.getCode());
			resourceService.delete(ids);
			this.setAjaxMsg(response, "{success:true,msg:'删除成功'}");
		}else{
			this.setAjaxMsg(response, "{success:false,msg:'实例为空'}");
		}
	}
	public SysResourceService getResourceService() {
		return resourceService;
	}

	@Autowired
	public void setResourceService(SysResourceService resourceService) {
		this.resourceService = resourceService;
	}
}
