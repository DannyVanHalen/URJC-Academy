package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.AlumnoRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Controller
public class AlumnoController extends UsuarioController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private AlumnoRepository repository;
	
}
