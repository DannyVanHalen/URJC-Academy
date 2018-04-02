package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD

import com.dad.urjcacademy.repository.ProfesorRepository;

@Controller
public class ProfesorController extends UsuarioController {

	@Autowired
	private ProfesorRepository repository;
		
=======
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;
import com.dad.urjcacademy.services.ProfesorService;
import com.dad.urjcacademy.services.UsuarioService;

@Controller
@RequestMapping("/profesor")
public class ProfesorController extends UsuarioController {

	@Autowired
	private UsuarioService usuarios;
	
	@Autowired
	private ProfesorService profesores;
	
		
	
>>>>>>> refs/remotes/origin/develop
}
