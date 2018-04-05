package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;
import com.dad.urjcacademy.services.ProfesorService;

@Controller
@RequestMapping("/profesor")
public class ProfesorController extends UsuarioController {

	@Autowired
	private ProfesorService profesores;
	
	@Autowired
	private ProfesorService profesores;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String profesor(Model model, @PathVariable long id){
		Profesor profesor = profesores.findById(id);
		if(profesor != null) {
			model.addAttribute("nombre",profesor.getNombre());
			model.addAttribute("apellido",profesor.getApellido());
			model.addAttribute("asignaturas",profesor.getAsignaturas());
			model.addAttribute("nombre",profesor.getNombre());
		}
		return null;
		
	}
		
}
