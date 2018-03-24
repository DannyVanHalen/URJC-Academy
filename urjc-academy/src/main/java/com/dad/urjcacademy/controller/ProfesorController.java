package com.dad.urjcacademy.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.services.ProfesorService;
import com.dad.urjcacademy.services.UsuarioService;

@Controller
@RequestMapping("/profesor")
public class ProfesorController extends UsuarioController {

	@Autowired
	private UsuarioService usuarios;
	
	@Autowired
	private ProfesorService profesores;
	
	@RequestMapping(value="" , method=RequestMethod.GET)
	public String loadProfesor(Model model, HttpServletRequest request) {

		Profesor profesor = (Profesor) usuarios.findByLogin(request.getUserPrincipal().getName());
		
		if(profesor != null) {
			model.addAttribute("nombre", profesor.getNombre() + " " + profesor.getApellido());
			model.addAttribute("asignaturas", profesor.getAsignaturas());
			model.addAttribute("tutorias", profesor.getTutoriasProfesor());
			return "profesor";
		}
		
		return "redirect:/loginError";
	}
		
	
}
