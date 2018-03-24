package com.dad.urjcacademy.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.dad.urjcacademy.services.AlumnoService;
import com.dad.urjcacademy.services.UsuarioService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController extends UsuarioController {

	@Autowired
	private UsuarioService usuarios;
	
	@Autowired
	private AlumnoService alumnos;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String loadAlumno(Model model, HttpServletRequest request) {
		
		Alumno alumno = (Alumno) usuarios.findByLogin(request.getUserPrincipal().getName());
		
		if(alumno != null) {
			model.addAttribute("nombre", alumno.getNombre() + " " + alumno.getApellido());
			model.addAttribute("asignaturas", alumno.getAsignaturasAlumno());
			model.addAttribute("tutorias", alumno.getTutoriasAlumno());
			return "alumno";
		}
		
		return "redirect:/loginError";
		
	}
	
	
}
