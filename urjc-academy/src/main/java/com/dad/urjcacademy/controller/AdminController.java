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
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.generator.PassGenerator;
import com.dad.urjcacademy.repository.AlumnoRepository;
import com.dad.urjcacademy.repository.AsignaturaRepository;
import com.dad.urjcacademy.repository.TitulacionRepository;
import com.dad.urjcacademy.repository.UsuarioRepository;
import com.dad.urjcacademy.repository.ProfesorRepository;



@Controller
@RequestMapping("/root")
public class AdminController extends UsuarioController{
	
	
	@Autowired
	private TitulacionRepository titulaciones;
	
	@Autowired
	private AsignaturaRepository asignaturas;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private ProfesorRepository profesores;
	
	@Autowired
	private AlumnoRepository alumnos;
	
	Titulacion titulacion = null;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String root(Model model) {
		List<Profesor> profesores = this.profesores.findAll();
		List<Alumno> alumnos = this.alumnos.findAll();
		List<Titulacion> titulaciones = this.titulaciones.findAll();
		
		/** Model **/
		model.addAttribute("profesores", profesores);
		model.addAttribute("alumnos", alumnos);
		model.addAttribute("titulaciones",titulaciones);
		
		
		return "root";
	}
	
	/** Altas Usuarios **/
	
	@RequestMapping(value="/alta-usuario", method=RequestMethod.GET)
	public String alta_usuario(Model model) {
		return "alta-usuario";
	}
	
	@RequestMapping(value="/nuevo-usuario", method=RequestMethod.POST)
	public String nuevo_usuario(Model model,
			@RequestParam String nombre, @RequestParam String apellido, @RequestParam String tlf,
			@RequestParam String maiLogin, @RequestParam String rol) {
		
		Usuario usuario = null;
		Alumno alumno = null;
		Profesor profesor = null;
		boolean esProfesor = rol.contains("profesor");

		
		/** creamos parcialmente el login a falta de tener el id de usuario registrado en la tabla **/

		char initName = Character.toLowerCase(nombre.charAt(0));
		char initLastName = Character.toLowerCase(apellido.charAt(0));
		String lastName = apellido.substring(1);
		String login = String.valueOf(initName)+String.valueOf(initLastName)+lastName;
		
		/** Creamos una contraseña de forma aleatoria **/
		String pass = new PassGenerator().getPassword(); 
		
		/** Registro de nuevo Usuario **/
		
		if(esProfesor) {
			usuario = usuarios.save(new Profesor(login,maiLogin,pass,rol,nombre,apellido,tlf,new ArrayList<Tutoria>(),new ArrayList<Asignatura>()));
			profesor = (Profesor) usuarios.findByLogin(login);
			if(profesor != null) {
				profesor.setLogin(login+String.valueOf(profesor.getId()));
				usuario = usuarios.save(profesor);
				profesores.save(profesor);
			} else {
				return "403";
			}
		} else {
			usuario = usuarios.save(new Alumno(login,maiLogin,pass,rol,nombre,apellido,tlf,new ArrayList<Tutoria>(),new ArrayList<Asignatura>()));
			alumno = (Alumno) usuarios.findByLogin(login);
			if(alumno != null) {
				alumno.setLogin(login+String.valueOf(alumno.getId()));
				usuario = usuarios.save(alumno);
				alumnos.save(alumno);
			} else {
				return "403";
			}
		}
		
		/** Mostramos los datos creados **/
		
		if(usuario != null) {
			model.addAttribute("rol", usuario.getRol());
			model.addAttribute("login", usuario.getLogin());
			model.addAttribute("maiLogin", usuario.getMaiLogin());
			model.addAttribute("pass", usuario.getPass());
			if(esProfesor) {
				model.addAttribute("nombre", profesor.getNombre());
				model.addAttribute("apellido", profesor.getApellido());
				model.addAttribute("tlf",profesor.getTlf());
			} else {
				model.addAttribute("nombre", alumno.getNombre());
				model.addAttribute("apellido", alumno.getApellido());
				model.addAttribute("tlf", alumno.getTlf());
			}
		} else {
			return "403";
		}
		
		
		return "nuevo-usuario";
	}
	
	
	@RequestMapping(value="/baja-usuario", method=RequestMethod.GET)
	public String formulario_baja_usuario(Model model) {
		return "baja-usuario";
	}
	
	@RequestMapping(value="/baja-usuario/elimina", method=RequestMethod.GET)
	public String elimina_usuario(Model model, @RequestParam String login) {

		
		return "403";
	
	}
	
	
	
	
	
}
