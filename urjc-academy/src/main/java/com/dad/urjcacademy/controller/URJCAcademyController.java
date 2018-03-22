package com.dad.urjcacademy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.UsuarioRepository;
import com.dad.urjcacademy.services.UsuarioService;


@Controller
public class URJCAcademyController {
	
	@RequestMapping(value= "/", method=RequestMethod.GET)
	public String urjc_academy_index(Model model) {
		return "index";
	}
	
	
	
}
