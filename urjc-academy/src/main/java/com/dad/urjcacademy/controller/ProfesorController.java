package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dad.urjcacademy.repository.AsignaturaRepository;
import com.dad.urjcacademy.repository.ProfesorRepository;

@Controller
@RequestMapping("/profesores/{id}")
public class ProfesorController extends UsuarioController {

	@Autowired
	private ProfesorRepository repository;
	
	@Autowired
	private AsignaturaRepository asignaturas;
		
}
