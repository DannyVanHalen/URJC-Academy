package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.repository.AsignaturaRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/asignatura")
public class AsignaturaController {

	@Autowired
	private AsignaturaRepository repository;
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public String asignatura(Model model, @PathVariable long id) {
		
		Asignatura asignatura = repository.findOne(id);
		
		model.addAttribute("nombre", asignatura.getNombre());
		model.addAttribute("plazas", asignatura.getPlazas());
		model.addAttribute("profesorNombre",asignatura.getProfesor().getNombre());
		model.addAttribute("profesorApellido", asignatura.getProfesor().getApellido());
		
		if(asignatura.getAlumnos().size() > 0) {
			model.addAttribute("hayAlumnos", true);
			model.addAttribute("alumnos", asignatura.getAlumnos());
		} else {
			model.addAttribute("noHayAlumnos", true);
		}
		
		return "asignatura";
	}
	
}
