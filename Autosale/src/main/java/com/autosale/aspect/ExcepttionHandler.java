package com.autosale.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExcepttionHandler {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e){
	ModelAndView mav = new ModelAndView("exception");
	mav.addObject("eText", e.getMessage());
	mav.addObject("sourceClass", e.getClass().getSimpleName());
		return mav;
		
		
	}
	

}
