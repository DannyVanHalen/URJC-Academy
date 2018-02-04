package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dad.urjcacademy.repository.AdminRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Controller
public class URJCAcademyController {

	@Autowired 
	private UsuarioRepository useRepository;
	
	@Autowired
	private AdminRepository admin;
	
	
	@RequestMapping("/")
	public String urjc_academy(Model model) {
		return "urjc-academy";
	}
	
	
	
}
