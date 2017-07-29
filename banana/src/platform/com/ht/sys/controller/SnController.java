/**
* @Package com.bluefat.budget.controller
* @Description: 单据编号
* @author qj  
* @date 2013-12-9 13:36:00 
* @version V1.0
*/
package com.ht.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.Sn;
import com.ht.sys.service.impl.SnService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/**
* 类功能说明: 单据编号
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>该代码由系统自动产生，请按需求修改</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-12-9 13:36:00
* @version V1.0
*/

@Controller("sys_SnController")
@RequestMapping("sys/sn")
public class SnController extends BaseController<Sn>{

	private SnService snService;
	
	/**
	 * @Description: 单据编号管理界面
	 * @author qj  
	 * @date 2013-12-9 13:36:00
	 * @return
	 */
	@RequestMapping("listPage")
	public String listPage(){
		return "sys/sn_list";
	}
	
	
	/**
	 * @Description: 单据编号查询 
	 * @author qj  
	 * @date 2013-12-9 13:36:00
	 * @param request
	 * @param response
	 */
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			Pager<Sn> pager) {
		try{
			QueryUtil queryUtil = new QueryUtil();
			//queryUtil.putOrder("billType", "asc");
			
			snService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
			
			super.printLog(request, "单据编号查询");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "单据编号查询", e);
			super.setGridJson(response, "单据编号出现异常，请联系管理员");
		}
	}
	
	/**
	 * @Description: 保存单据编号信息
	 * @author qj  
	 * @date 2013-12-9 13:36:00 
	 * @param request
	 * @param response
	 * @param fieldValue
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, Sn sn){
		try{
			snService.save(sn);
			super.printLog(request, "保存单据编号信息");
			super.setAjaxMsg(response, true, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "保存单据编号信息", e);
			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * @Description: 根据id查找单据编号信息
	 * @author qj  
	 * @date 2013-12-9 13:36:00
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request, HttpServletResponse response,
			int id){
		try{
			Sn sn = snService.get(id);
			super.printLog(request, "根据id查找单据编号信息");
			super.setExtJsFormJson(response, true, sn);
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找单据编号信息", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}
	
	
	/**
	 * @Description: 更新单据编号信息
	 * @author qj  
	 * @date 2013-12-9 13:36:00
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, Sn sn){
		try{
			snService.update(sn);
			super.printLog(request, "更新单据编号信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "更新单据编号信息", e);
			super.setAjaxMsg(response, false, "更新失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * 获取流水号的时间规则
	 * 
	 * */
	@RequestMapping("getRule")
	public void getRule(HttpServletRequest request, HttpServletResponse response){
		try{
			Map<String, String> map = new HashMap<String, String>();
			map.put("yyyy", "年(yyyy)");
			map.put("yyyyMM", "年月(yyyyMM)");
			map.put("yyyyMMdd", "年月日(yyyyMMdd)");
			map.put("yyyyMMddHHmmss", "时间(yyyyMMddHHmmss)");
			super.setComboJson(response, map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public SnService getSnService() {
		return snService;
	}

	@Autowired
	public void setSnService(SnService snService) {
		this.snService = snService;
	}
}
