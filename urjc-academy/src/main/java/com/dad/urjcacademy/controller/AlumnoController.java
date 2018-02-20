package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.AlumnoRepository;

@Controller
public class AlumnoController extends UsuarioController {

	@Autowired
	private AlumnoRepository repository;
	
}
