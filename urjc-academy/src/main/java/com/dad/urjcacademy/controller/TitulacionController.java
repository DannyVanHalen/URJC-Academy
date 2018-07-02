package com.dad.urjcacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.repository.TitulacionRepository;
import com.dad.urjcacademy.services.TitulacionService;

@Controller
@RequestMapping("/titulacion")
public class TitulacionController {

	@Autowired
	private TitulacionService titulaciones;
	@Autowired
	private TitulacionRepository repository;
	
	
	
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
	
	@GetMapping("/buscar")
	public String buscarTitulacion(Model model) {
		return "buscar-form";
	}
	
	@GetMapping("/buscar/listar")
	public String listarBusqueda(Model model, 
			@RequestParam String rama ) {
		
			model.addAttribute("rama",rama);
			model.addAttribute("lista", titulaciones.listarPorRama(rama));
		
		return "list-por-rama";
	}
	
	

	
	
}
