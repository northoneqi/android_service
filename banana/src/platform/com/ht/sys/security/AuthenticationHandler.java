package com.ht.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ht.sys.controller.BaseController;

/** 
 * <p>类功能说明: springSecurity认证成功和失败后的处理方式</p>
 * <p>Title: AuthenticationHandler.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-8-5 上午09:15:59
 * @version V1.0
 */

@Component("authenticationHandler")
public class AuthenticationHandler extends BaseController<Object> 
		implements AuthenticationSuccessHandler,AuthenticationFailureHandler{

	
	/**
	 * <p>认证成功处理</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:46:44 
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.setAjaxMsg(response, true, "登陆成功");
	}

	/**
	 * <p>认证失败处理</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:46:44 
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		super.setAjaxMsg(response, false, exception.getMessage());
	}

}
