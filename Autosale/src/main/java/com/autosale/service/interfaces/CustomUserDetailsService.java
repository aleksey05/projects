package com.autosale.service.interfaces;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
	
	void addNewUser(Map<String, String> requestParams);

}
