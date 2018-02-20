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

@Controller
@RequestMapping("/profesor")
public class ProfesorController extends UsuarioController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private ProfesorRepository profesores;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String profesor(Model model, @PathVariable long id) {
		
		Profesor profesor = (Profesor) usuarios.findOne(id);
		
		if(profesor != null) {
			model.addAttribute("nombre", profesor.getNombre() + " " + profesor.getApellido());
			model.addAttribute("asignaturas", profesor.getAsignaturas());
			model.addAttribute("tutorias", profesor.getTutorias());
			return "profesor";
		}
		
		return "404";
	
	}
	
	
		
}
