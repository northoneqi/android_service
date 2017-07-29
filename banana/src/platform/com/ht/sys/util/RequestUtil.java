package com.ht.sys.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
* <p>类功能说明:request请求帮助类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午11:06:17
* @version V1.0
*/
public class RequestUtil {

	
	/**
	 * <p>打印request请求传过来的参数</p>
	 * @author qj
	 * @date 2013-11-15 上午11:06:47
	 * @param @param request    
	 * @return void   
	 * @throws
	 */
	public static void printAttritbute(HttpServletRequest request){
		Enumeration<?> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					System.out.println("参数：" + paramName + "=" + paramValue);
				}
			}
		}
	}
}
