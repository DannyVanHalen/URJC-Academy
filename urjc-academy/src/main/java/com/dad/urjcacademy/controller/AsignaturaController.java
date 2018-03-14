package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.services.AsignaturaService;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

	@Autowired
	private AsignaturaService asignaturas;
	
	
	
	
}
