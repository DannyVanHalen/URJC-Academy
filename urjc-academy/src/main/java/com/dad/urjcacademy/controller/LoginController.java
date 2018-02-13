package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Controller
public class LoginController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String login_access(Model model,
			@RequestParam String login, @RequestParam String pass) {
		
		Usuario usuario = usuarios.findByLoginAndPass(login, pass);
		
		if(usuario != null) {
			if(usuario.getRol().contains("administrador") || usuario.getLogin().contains("root")) {
				return "root";
			}
		}
		
		return "";
	}
	
}
