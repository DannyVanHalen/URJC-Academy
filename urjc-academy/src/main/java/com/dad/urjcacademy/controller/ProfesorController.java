package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.ProfesorRepository;

@Controller
public class ProfesorController extends UsuarioController {

	@Autowired
	private ProfesorRepository repository;
		
}
