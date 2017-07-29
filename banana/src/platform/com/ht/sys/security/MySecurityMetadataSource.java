package com.ht.sys.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.ht.sys.bean.MRoleResource;
import com.ht.sys.dao.impl.RoleResourceDao;

/**
*<p> 类功能说明:加载资源与权限的对应关系</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:42:37
* @version V1.0
*/
@Component("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	//由spring调用
	private RoleResourceDao roleResourceDao;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;


	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	//加载所有资源与权限的关系
	private void loadResourceDefine() {
		if(resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			
			//查找资源对应的角色信息
			List<MRoleResource> roleResources = this.roleResourceDao.getAllResource();
			
			for (MRoleResource roleRes : roleResources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                //以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_"+roleRes.getRoleCode());
				configAttributes.add(configAttribute);
				resourceMap.put("/"+roleRes.getUrl(), configAttributes);
			}
		}
		
		//Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
		//Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();
		
	}
	
	/**
	 * <p>返回所请求资源所需要的权限</p>
	 * */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(resourceMap == null) {
			loadResourceDefine();
		}
		return null;  //开发的时候
		//return resourceMap.get(requestUrl);
	}

	public RoleResourceDao getRoleResourceDao() {
		return roleResourceDao;
	}

	@Autowired
	public void setRoleResourceDao(RoleResourceDao roleResourceDao) {
		this.roleResourceDao = roleResourceDao;
	}
}