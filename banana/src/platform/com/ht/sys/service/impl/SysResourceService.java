/**
* @Title: SysResourceService.java 
* @Package com.bluefat.sys.service.impl 
* @Description: 资源管理
* @author qj  
* @date 2013-8-23 下午3:05:49 
* @version V1.0
*/
package com.ht.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysResource;
import com.ht.sys.core.function.Accordion;
import com.ht.sys.core.function.Button;
import com.ht.sys.core.function.Menu;
import com.ht.sys.dao.impl.SysResourceDao;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明:资源服务类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:53:47
* @version V1.0
*/
@Service("sys_resourceService")
public class SysResourceService extends SysService<SysResource>{

	private SysResourceDao resourceDao;

	/**
	 * <p>得到单位部门树</p>
	 * @author qj  
	 * @date 2013-8-13 上午10:45:35 
	 * @param queryUtil  查询条件
	 * @return  返回资源树
	 */
	public List<SysResource> getTree(QueryUtil queryUtil){
		return resourceDao.getTree(queryUtil);
	}
	
	public void deleteRoleResource(String resourceCode){
		resourceDao.deleteRoleResource(resourceCode);
	}
	
	/**
	 * <p>批量保存菜单信息</p>
	 * @author qj  
	 * @date 2013-8-23 下午9:11:48 
	 * @param accordions
	 */
	public void saveAccordion(List<Accordion> accordions){
		
		resourceDao.delete(); //清空资源信息
		for(Accordion accordion: accordions){
			
			//保存accordion
			SysResource resource = new SysResource();
			resource.setCode(accordion.getId());
			resource.setName(accordion.getName());
			resource.setType("accordion");
			resource.setIcon("accod_"+accordion.getId()+".png");
			resource.setState("启用");
			resource.setParentCode("root");
			//if(accordion.g)
			resourceDao.save(resource);
			
			if(accordion.getMenus() != null) {
				this.saveMenu(accordion.getMenus(), accordion.getId());
			}
			
			resourceDao.clear(); //刷新缓存
		}
	}
	
	private void saveMenu(List<Menu> menus, String parentCode){
		if(menus.size() == 0) return;
		//保存menu
		for(Menu menu: menus){
			SysResource mResource = new SysResource();
			mResource.setCode(menu.getId());
			mResource.setIcon(menu.getIcon());
			mResource.setName(menu.getName());
			mResource.setParentCode(parentCode);
			mResource.setState("启用");
			mResource.setUrl(menu.getUrl());
			mResource.setType("menu");
			resourceDao.save(mResource);
			
			//保存button
			if(menu.getButtons() != null) {
				for(Button button: menu.getButtons()){
					SysResource bResource = new SysResource();
					bResource.setCode(button.getId());
					bResource.setName(button.getName());
					bResource.setIcon(button.getIcon());
					bResource.setParentCode(menu.getId());
					bResource.setType("button");
					bResource.setState("启用");
					
					resourceDao.save(bResource);
				}
			}
			//保存子菜单
			if(menu.getChildren() != null) {
				saveMenu(menu.getChildren(), menu.getId());
			}
			resourceDao.clear(); //刷新缓存
		}
	}
	
	/**
	 * <p>根据url得到资源信息</p>
	 * @author qj
	 * @date 2013-12-6 上午09:58:51
	 * @param url   url
	 * @return SysResource   返回得到的资源信息，如果没有查找到则返回"null"
	 * @throws
	 */
	public SysResource getByUrl(String url){
		List<SysResource> list = resourceDao.get(new String[]{"url=?"},new String[]{url});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	public SysResourceDao getResourceDao() {
		return resourceDao;
	}

	@Autowired
	public void setResourceDao(SysResourceDao resourceDao) {
		super.setSysDao(resourceDao);
		this.resourceDao = resourceDao;
	}
	
}
