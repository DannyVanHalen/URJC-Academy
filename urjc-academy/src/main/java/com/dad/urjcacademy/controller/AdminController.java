package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dad.urjcacademy.services.AlumnoService;
import com.dad.urjcacademy.services.AsignaturaService;
import com.dad.urjcacademy.services.ProfesorService;
import com.dad.urjcacademy.services.TutoriaService;
import com.dad.urjcacademy.services.UsuarioService;

@Controller
@RequestMapping("/root")
public class AdminController extends UsuarioController{
	
	@Autowired
	private UsuarioService usuarios;

	@Autowired
	private AlumnoService alumnos;
	
	@Autowired
	private ProfesorService profesores;
	
	@Autowired
	private AsignaturaService asignaturas;
	
	@Autowired
	private TutoriaService tutorias;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String root(Model model) {
		return "root";
	}
	
	
}
