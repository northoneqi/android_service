package com.ht.sys.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysUser;
import com.ht.sys.dao.impl.SysUserDao;

@Service("userDetailsService")
public class MyUserDetailServiceImpl implements UserDetailsService {
	@Resource
	private SysUserDao userDao;

	public SysUserDao getUsersDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(SysUserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
	
		SysUser pubUser = this.userDao.findByName(username);
		if (pubUser == null) {
			throw new UsernameNotFoundException(username);
		}
		Collection<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		grantedAuths.add(new GrantedAuthorityImpl("ROLE_"+pubUser.getRoleCode()));

		String password = pubUser.getPassword();
		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		User userdetail = new User(username, password, enables,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuths);
		return userdetail;
	}

	// 取得用户的权限
	/*
	 * private Set<GrantedAuthority> obtionGrantedAuthorities(Users user) {
	 * Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
	 * Set<Roles> roles = user.getRoles();
	 * 
	 * for(Roles role : roles) { Set<Resources> tempRes = role.getResources();
	 * for(Resources res : tempRes) { authSet.add(new
	 * GrantedAuthorityImpl(res.getName())); } } return authSet; }
	 */
}