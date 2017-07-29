/**
* @Package com.bluefat.sys.controller
* @Description: 系统基本信息
* @author qj  
* @date 2014-2-25 9:10:11 
* @version V1.0
*/
package com.ht.sys.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.SysInfo;
import com.ht.sys.service.impl.SysInfoService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

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

@Controller("sys_SysInfoController")
@RequestMapping("sys/info")
public class SysInfoController extends BaseController<SysInfo>{

	private SysInfoService sysInfoService;
	
	/**
	 * @Description: 系统基本信息管理界面
	 * @author qj  
	 * @date 2014-2-25 9:10:11
	 * @return
	 */
	@RequestMapping("showInfo")
	public String showInfo(HttpServletRequest request, HttpServletResponse response,
			Model model){
		model.addAttribute("department",departmentService.getParentDepartment());
		return "sys/sys_info";
	}
	
	
	/**
	 * @Description: 系统基本信息查询 
	 * @author qj  
	 * @date 2014-2-25 9:10:11
	 * @param request
	 * @param response
	 */
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			Pager<SysInfo> pager) {
		try{
			QueryUtil queryUtil = new QueryUtil();
			
			sysInfoService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
			
			super.printLog(request, "系统基本信息查询");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "系统基本信息查询", e);
			super.setGridJson(response, "系统基本信息出现异常，请联系管理员");
		}
	}
	
	/**
	 * @Description: 保存系统基本信息信息
	 * @author qj  
	 * @date 2014-2-25 9:10:11 
	 * @param request
	 * @param response
	 * @param sysInfo
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, SysInfo sysInfo){
		try{
			sysInfoService.update(sysInfo);
			super.printLog(request, "保存系统基本信息信息");
			super.setAjaxMsg(response, true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "保存系统基本信息信息", e);
			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * @Description: 根据id查找系统基本信息信息
	 * @author qj  
	 * @date 2014-2-25 9:10:11
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request, HttpServletResponse response,
			String id){
		try{
			SysInfo sysInfo = sysInfoService.get(id);
			super.printLog(request, "根据id查找系统基本信息信息");
			super.setExtJsFormJson(response, true, sysInfo);
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找系统基本信息信息", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}
	
	
	/**
	 * @Description: 更新系统基本信息信息
	 * @author qj  
	 * @date 2014-2-25 9:10:11
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, SysInfo sysInfo){
		try{
			sysInfoService.update(sysInfo);
			super.printLog(request, "更新系统基本信息信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "更新系统基本信息信息", e);
			super.setAjaxMsg(response, false, "更新失败! 可能原因：编号重复");
		}
	}
	
	
	/**
	 * @Description: 删除系统基本信息信息 
	 * @author qj  
	 * @date 2014-2-25 9:10:11
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] ids){
		try{
			sysInfoService.delete((Serializable[])ids);
			super.printLog(request, "删除系统基本信息信息");
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除系统基本信息信息", e);
			super.setAjaxMsg(response, false, "删除失败！可能原因：该系统基本信息正在被使用。");
		}
	}
	
	public SysInfoService getSysInfoService() {
		return sysInfoService;
	}

	@Autowired
	public void setSysInfoService(SysInfoService sysInfoService) {
		this.sysInfoService = sysInfoService;
	}
}
