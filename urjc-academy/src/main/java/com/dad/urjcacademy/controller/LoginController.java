package com.dad.urjcacademy.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dad.urjcacademy.security.UsuarioRepositoryAuthenticationProvider;


@Controller
public class LoginController {


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value="/logged", method=RequestMethod.GET)
	public String logged(Model model, HttpServletRequest request) {
		
		if(request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/root";
		}
		
		if(request.isUserInRole("ROLE_TEACHER")) {
			return "redirect:/profesor";
		}
		
		if(request.isUserInRole("ROLE_STUDENT")) {
			return "redirect:/alumno";
		}
		
		return "redirect:/loginError";
	}
	
	@RequestMapping(value="/loginError", method=RequestMethod.GET)
	public String loginError(Model model) {
		return "loginError";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		try {
			request.logout();
			return "redirect:/login";
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return "redirect:/";
		
	}
	
	
}
