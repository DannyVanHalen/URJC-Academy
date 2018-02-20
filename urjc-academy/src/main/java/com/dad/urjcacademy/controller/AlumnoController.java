package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.AlumnoRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Controller
@RequestMapping("/alumno")
public class AlumnoController extends UsuarioController {

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private AlumnoRepository alumnos;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String alumno(Model model, @PathVariable long id) {
		
		Alumno alumno = (Alumno) usuarios.findOne(id);
		
		if(alumno != null) {
			
			model.addAttribute("nombre", alumno.getNombre() + " " + alumno.getApellido());
			model.addAttribute("asignaturas", alumno.getAsignaturas());
			model.addAttribute("tutorias", alumno.getTutorias());
			
			return "alumno";
			
		}
		
		return "404";
	
	}
	
}
