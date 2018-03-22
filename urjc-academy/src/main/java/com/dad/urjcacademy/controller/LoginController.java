package com.dad.urjcacademy.controller;


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
	public String logged(Model model, 
			UsuarioRepositoryAuthenticationProvider authentication) {
		return "";
	}
	
	
}
