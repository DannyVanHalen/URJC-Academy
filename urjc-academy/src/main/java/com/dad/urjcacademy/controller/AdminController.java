package com.dad.urjcacademy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.services.AlumnoService;
import com.dad.urjcacademy.services.AsignaturaService;
import com.dad.urjcacademy.services.ProfesorService;
import com.dad.urjcacademy.services.TitulacionService;
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
	
	@Autowired
	private TitulacionService titulaciones;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String root(Model model) {
		
		//Lectura de datos
		//titulación
		model.addAttribute("titulaciones", titulaciones.findAll());
		
		return "root";
	}
	
	/*Administración de Titulaciones*/
	
	// Formulario de Alta
	@RequestMapping(value="/alta-titulacion", method=RequestMethod.GET)
	public String formularioAltaTitulacion(Model model) {
		return "alta-titulacion";
	}
	
	// Alta Titulacion
	@RequestMapping(value="/nueva-titulacion", method=RequestMethod.POST)
	public String nuevaTitulacion(Model model,
			@RequestParam String nombre, @RequestParam String rama) {
		
		if(!nombre.equals(null) && !rama.equals(null)) {
			Titulacion titulacion = titulaciones.save(new Titulacion(nombre,rama,new ArrayList<>()));
			if(titulacion != null) {
				model.addAttribute("nombre", titulacion.getNombre());
				model.addAttribute("rama", titulacion.getRama());
				return "nueva-titulacion";
			}
		}
		
		return "505";
	}
	
	//Select Titulacion
	@RequestMapping(value="/titulacion/{id}", method=RequestMethod.GET)
	public String selectTitulacion(Model model, @PathVariable long id) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
			model.addAttribute("nombre", titulacion.getNombre());
			model.addAttribute("rama", titulacion.getRama());
			model.addAttribute("asignaturas", titulacion.getAsignaturas());
			model.addAttribute("soyRoot", true);
			model.addAttribute("usuarioFinal", false);
			return "titulacion";
		}
		
		return "404";
	}
	
	//Borado Titulacion
	
	@RequestMapping(value="/titulacion/borrar/{id}", method=RequestMethod.GET)
	public String borrarTitulacion(Model model, @PathVariable long id) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
			titulaciones.delete(id);
			if(!titulaciones.exists(id)) {
				model.addAttribute("nombre", titulacion.getNombre());
				return "eliminado";
			}
		}else {
			return "404";
		}
		
		return "505";
		
	}
	
	
	
	
}
