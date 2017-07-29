/**
* @Title: DynamicDataSource.java 
* @Package com.bluefat.sys.util.db 
* @Description: TODO 
* @author qj  
* @date 2013-7-19 下午12:00:09 
* @version V1.0
*/
package com.ht.sys.util.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* <p>类功能说明: 动态数据源切换</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午11:08:44
* @version V1.0
*/
public class DynamicDataSource extends AbstractRoutingDataSource{

	private static ThreadLocal<String> local = new ThreadLocal<String>();

	@Override
	protected Object determineCurrentLookupKey() {
		 return local.get() == null ? "dataSource" : local.get();
	}

    /**
     * <p>设置数据源路径</p>
     * @author qj  
     * @date 2013-7-19 下午12:02:57 
     * @param route  数据源路径
     */
    public static void setRoute(String route) {
        if (route==null || route.equals("")){
            route = "dataSource";
        }
        local.set(route);
    }

}
