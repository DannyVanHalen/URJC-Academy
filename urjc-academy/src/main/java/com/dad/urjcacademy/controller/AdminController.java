package com.dad.urjcacademy.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.entity.Asignatura;

import com.dad.urjcacademy.repository.AlumnoRepository;
import com.dad.urjcacademy.repository.AsignaturaRepository;
import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.repository.TitulacionRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;



@Controller
@RequestMapping("/root")
public class AdminController extends UsuarioController{
	
	@Autowired
	private TitulacionRepository titulaciones;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private AsignaturaRepository asignaturas;
	
	@Autowired
	private ProfesorRepository profesores;
	
	@Autowired
	private AlumnoRepository alumnos;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String root(Model model) {
		List<Titulacion> titulaciones = this.titulaciones.findAll();
		List<Profesor> profesores = this.profesores.findAll();
		List<Alumno> alumnos = this.alumnos.findAll();
		
		model.addAttribute("titulaciones", titulaciones);
		model.addAttribute("profesores", profesores);
		model.addAttribute("alumnos", alumnos);
		
		return "root";
	}
	
	
	@RequestMapping(value="/alta-usuario", method=RequestMethod.GET)
	public String alta_usuario(Model model) {
		return "alta-usuario";
	}
	
	@RequestMapping(value="/nuevo-usuario", method=RequestMethod.POST)
	public String nuevo_usuario(Model model,
			@RequestParam String nombre, @RequestParam String apellido, @RequestParam String tlf,
			@RequestParam String maiLogin, @RequestParam String pass, @RequestParam String rol) {
		
		/** generamos automaticamente el login de usuario concatenando la primera inicial del nombre con el apellido del usuario **/
		char initName = Character.toLowerCase(nombre.charAt(0));
		char initLastName = Character.toLowerCase(apellido.charAt(0));
		String lastName = apellido.substring(1);
		String login = String.valueOf(initName)+String.valueOf(initLastName)+lastName;
		
		Usuario ok = null;
		
		if(rol.contains("profesor")) 
			ok = profesores.save(new Profesor(login,maiLogin,pass,rol,nombre,apellido,tlf,new ArrayList<Tutoria>(),new ArrayList<Asignatura>()));
		 else if (rol.contains("alumno")) 
			ok = alumnos.save(new Alumno(login,maiLogin,pass,rol,nombre,apellido,tlf,new ArrayList<Tutoria>(),new ArrayList<Asignatura>()));
		
		
		if(ok != null) {
			
			usuarios.save(ok);
			model.addAttribute("rol", rol);
			model.addAttribute("nombre", nombre);
			model.addAttribute("apellido", apellido);
			model.addAttribute("tlf",tlf);
			model.addAttribute("maiLogin", maiLogin);
			model.addAttribute("pass", pass);
			model.addAttribute("login", login);
			
			return "nuevo-usuario";
			
		}
		
		return "403";
	}
	
	
}
