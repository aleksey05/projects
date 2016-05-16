package com.autosale.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.autosale.dao.RoleDao;
import com.autosale.dao.UserDao;
import com.autosale.model.Car;
import com.autosale.model.Role;
import com.autosale.model.User;
import com.autosale.model.UserStatus;
import com.autosale.service.interfaces.CustomUserDetailsService;

@Component
@Qualifier("userDetailsService")
public class UserDetailsServiceImpl implements CustomUserDetailsService {

	public static final int USER_ROLE_ID = 2;
	
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

	@Transactional
	public void addNewUser(Map<String, String> requestParams) {
		List<Role> userRoleList = new ArrayList<Role>();
		userRoleList.add(roleDao.getRoleById(Integer.valueOf(USER_ROLE_ID)));
		User newUser = new User();
		newUser.setName(requestParams.get("name"));
		newUser.setPhone_umber(requestParams.get("phone"));
		newUser.setEmail(requestParams.get("email"));
		newUser.setPassword(requestParams.get("password"));
		newUser.setRoles(userRoleList);
		newUser.setStatus(UserStatus.ACTIVE);
		newUser.setCars(new ArrayList<Car>());
		userDao.addUser(newUser);

	}
	
//	private User setUserProperties(){
//		User user = new User();
//		
//		
//		return user;
//		
//		
//		
//	}
	
}