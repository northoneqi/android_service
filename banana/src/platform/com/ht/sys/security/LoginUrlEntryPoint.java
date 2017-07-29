package com.ht.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
* <p>类功能说明: 登录切入点</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:41:11
* @version V1.0
*/
public class LoginUrlEntryPoint implements AuthenticationEntryPoint{

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		String targetUrl = null;
        String url = request.getRequestURI();
   
        if(url.indexOf("admin") != -1){
            //未登录而访问后台受控资源时，跳转到后台登录页面
            targetUrl = "/admin/index";
        }else{
            //未登录而访问前台受控资源时，跳转到前台登录页面
            targetUrl = "/font/loginPage";
        }
   
        targetUrl = request.getContextPath() + targetUrl;
        response.sendRedirect(targetUrl);
		
	}

}
