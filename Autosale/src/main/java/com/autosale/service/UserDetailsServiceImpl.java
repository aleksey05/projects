package com.autosale.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.autosale.dao.RoleDao;
import com.autosale.dao.UserDao;
import com.autosale.dao.impl.UserDaoImpl;
import com.autosale.model.Role;
import com.autosale.model.User;
import com.autosale.model.UserStatus;


@Component
@Qualifier("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		org.springframework.security.core.userdetails.User securityUser = null;

		User user = userDao.getUserByName(userName);
		if (user != null) {
			String password = user.getPassword();
			boolean enabled = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean credentialsNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonLocked = user.getStatus().equals(UserStatus.ACTIVE);

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			}
			securityUser = new org.springframework.security.core.userdetails.User(userName, password, enabled,
					accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			return securityUser;
		} else {
			throw new UsernameNotFoundException("User not defined");
		}

	}

	/*@Transactional
	public void addNewUser(User user, String userRoleId) {
		List<Role> userRoleList = new ArrayList<Role>();
		User user = new User();
		user.setName(userVo.getName());
		user.setPassword(userVo.getPassword());
		userRoleList.add(roleDao.getRoleById(Integer.valueOf(userRoleId)));
		user.setRoles(userRoleList);
		user.setStatus(UserStatus.ACTIVE);
		userDao.addUser(user);

	}*/
}