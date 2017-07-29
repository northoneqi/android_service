/**
* @Title: SysDictController.java 
* @Package com.bluefat.sys.controller 
* @Description: 数据字典
* @author qj  
* @date 2013-8-18 下午5:58:43 
* @version V1.0
*/
package com.ht.sys.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.SysDict;
import com.ht.sys.service.impl.SysDictService;
import com.ht.sys.util.Constants;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明:数据字典</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午09:40:13
* @version V1.0
*/
@Controller("sys_dictController")
@RequestMapping("sys/dict")
public class SysDictController extends SysController<SysDict>{
	
	private SysDictService dictService;

	/**
	 * <p>添加字典页面</p>
	 * @author qj  
	 * @date 2013-8-18 下午6:01:58 
	 * @return
	 */
	@RequestMapping("listPage")
	public String addPage(){
		return "sys/dict_list";
	}
	
	/**
	 * <p>得到分组信息</p>
	 * @author qj  
	 * @date 2013-8-18 下午7:33:27 
	 * @param request
	 * @param response
	 */
	@RequestMapping("getDictGroup")
	public void getDictGroup(HttpServletRequest request, HttpServletResponse response){
		try{
			Map<String, String> map = Constants.getDictTypes();
			super.setComboJson(response, map);
			super.printLog(request, "查询数据字典分组信息");
		} catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询数据字典分组信息", e);
		}
		
	}
	
	/**
	 * <p> 通过分组编号得到数据字典</p>
	 * @author qj  
	 * @date 2013-8-28 上午10:18:55 
	 * @param request
	 * @param response
	 * @param code   字典分组编号
	 */
	@RequestMapping("getDictByCode")
	public void getDictByCode(HttpServletRequest request, HttpServletResponse response, String code){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("dCode", "=", code);
			queryUtil.putOrder("code", "asc");
			List<SysDict> list = dictService.findByCondition(queryUtil);
			super.setAjaxMsg(response, JSONArray.fromObject(list).toString());
			super.printLog(request, "通过分组编号查找字典信息");
		} catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "通过分组编号查找字典信息", e);
		}
		
	}
	
	/**
	 * <p>查询数据字典列表</p>
	 * @author qj  
	 * @date 2013-8-18 下午6:05:57 
	 * @param request
	 * @param response
	 * @param pager
	 * @param dCode  //字典分组编号
	 */
	@RequestMapping("queryList")
	public void queryList(HttpServletRequest request, HttpServletResponse response,
			Pager<SysDict> pager, String dCode){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("dCode", "asc");
			queryUtil.putOrder("code", "asc");
			
			if(!isEmpty(dCode)){
				queryUtil.put("dCode", "=", dCode);
			}
			
			dictService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
			super.printLog(request, "查询数据字典信息");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询数据字典信息", e);
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * <p>保存数据字典信息</p>
	 * @author qj  
	 * @date 2013-8-18 下午6:08:19 
	 * @param request
	 * @param response
	 * @param sysDict  数据字典实体
	 */
	@RequestMapping("save")
	public void save(HttpServletRequest request, HttpServletResponse response, SysDict sysDict){
		try{
			
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("code", "=", sysDict.getCode());
			List<SysDict> list = dictService.findByCondition(queryUtil);
			String dcode = sysDict.getdCode();
			if(dcode.equals("1009")){
                 if(sysDict.getName().equals("0")){
                	 super.setAjaxMsg(response, false, "保存失败! 可能原因：字典名称不能为0");
                 }else{
                	
                	 if(list.size() == 0) {
                		 dictService.save(sysDict);
                		 super.setAjaxMsg(response, true, "保存成功！");
                	 }else{ 
             			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
                	 }
     			}				
			}else{
					if(list.size() == 0) {
	           		 dictService.save(sysDict);
	           		 super.setAjaxMsg(response, true, "保存成功！");
	           	 }else{ 
	        			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
	           	 }
			}
			//super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
			super.printLog(request, "保存数据字典信息");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "保存数据字典信息信息", e);
			super.setAjaxMsg(response, false, "保存失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * <p>根据id查找数据字典信息</p>
	 * @author qj  
	 * @date 2013-8-18 下午6:08:57 
	 * @param request
	 * @param response
	 * @param id  数据字典id
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request, HttpServletResponse response, String id){
		try{
			SysDict sysDict = dictService.get(id);
			super.setExtJsFormJson(response, true, sysDict);
			super.printLog(request, "根据id查找数据字典信息");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "根据Id查找数据字典信息", e);
			super.setExtJsFormJson(response, false, e.getMessage());
		}
	}
	

	
	/**
	 * <p>更新数据字典信息</p>
	 * @author qj  
	 * @date 2013-8-18 下午6:11:30 
	 * @param request
	 * @param response
	 * @param sysDict  数据字典实体
	 */
	@RequestMapping("update")
	public void update(HttpServletRequest request, HttpServletResponse response, SysDict sysDict){
		try{
			dictService.update(sysDict);
			super.printLog(request, "更新数据字典信息");
			super.setAjaxMsg(response, true, "更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "更新数据字典信息", e);
			super.setAjaxMsg(response, false, "更新失败! 可能原因：编号重复");
		}
	}
	
	/**
	 * <p>删除数据字典</p>
	 * @author qj  
	 * @date 2013-8-18 下午6:12:14 
	 * @param request
	 * @param response
	 * @param ids  字典id数组
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, String[] ids){
		try{
			dictService.delete((Serializable[])ids);
			super.printLog(request, "删除数据字典信息");
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除数据字典信息", e);
			super.setAjaxMsg(response, false, "删除失败！可能原因：该职工正在被使用。");
		}
	}
	
	public SysDictService getDictService() {
		return dictService;
	}

	@Autowired
	public void setDictService(SysDictService dictService) {
		this.dictService = dictService;
	}
}
