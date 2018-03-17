package com.dad.urjcacademy.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.repository.AsignaturaRepository;
import com.dad.urjcacademy.repository.TitulacionRepository;
import com.dad.urjcacademy.services.TitulacionService;

@Controller
@RequestMapping("/titulacion")
public class TitulacionController {

	@Autowired
	private TitulacionService titulaciones;
	
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public String titulacion(Model model,
			@PathVariable long id) {
		
		Titulacion titulacion = titulaciones.findById(id);
		
		if(titulacion != null) {
	
			model.addAttribute("nombre",titulacion.getNombre());
			model.addAttribute("rama", titulacion.getRama());
			model.addAttribute("asignaturas", titulacion.getAsignaturas());
			model.addAttribute("soyRoot", false);
			model.addAttribute("usuarioFinal", true);
			return "titulacion";
			
		}
		
		return "404";
		
	}
	
	

	
	
}
