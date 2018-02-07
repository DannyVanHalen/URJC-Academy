package com.dad.urjcacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.repository.TitulacionRepository;

@Controller
@RequestMapping("/titulaciones")
public class TitulacionController {

	@Autowired
	private TitulacionRepository repository;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String titulaciones(Model model, @PathVariable long id) {
		
		Titulacion titulacion = repository.findOne(id);
		List<Asignatura> asignaturasTitulacion = titulacion.getAsignaturas();
		
		model.addAttribute("nombre", titulacion.getNombre());
		model.addAttribute("rama", titulacion.getRama());
		
		model.addAttribute("asignaturas", asignaturasTitulacion);
		model.addAttribute("root",true);
		
		return "titulaciones";
		
		
	}
	
	
}
