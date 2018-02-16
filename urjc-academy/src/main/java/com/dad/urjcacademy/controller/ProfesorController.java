package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Controller
public class ProfesorController extends UsuarioController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private ProfesorRepository repository;
		
}
