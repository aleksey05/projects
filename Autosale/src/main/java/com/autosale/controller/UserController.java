package com.autosale.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.autosale.service.interfaces.CustomUserDetailsService;

@Controller
@Transactional
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CustomUserDetailsService userService;

	@RequestMapping("/provide")
	public ModelAndView provideUser() {
		return new ModelAndView("user_form");

	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public ModelAndView acceptUser(@RequestParam Map<String, String> requestParams) {
		userService.addNewUser(requestParams);
		return new ModelAndView("redirect:/");

	}

}
