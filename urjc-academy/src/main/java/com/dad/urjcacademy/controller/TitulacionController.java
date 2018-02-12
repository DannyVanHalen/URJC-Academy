package com.dad.urjcacademy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.repository.TitulacionRepository;

@Controller
@RequestMapping("/root/titulacion")
public class TitulacionController {

	@Autowired
	private TitulacionRepository titulaciones;
	
	private Titulacion titulacion=null;
	
	/** Altas Titulaciones **/
	@RequestMapping(value="/alta-titulacion", method=RequestMethod.GET)
	public String alta_titulacion(Model model) {
		return "alta-titulacion";
	}
	
	@RequestMapping(value="/nueva-titulacion", method=RequestMethod.POST)
	public String nueva_titulacion(Model model,
			@RequestParam String nombre, @RequestParam String rama) {
		
		titulacion = titulaciones.save(new Titulacion(nombre,rama,new ArrayList<Asignatura>()));
		if(titulacion != null) {
			model.addAttribute("nombre", titulacion.getNombre());
			model.addAttribute("rama", titulacion.getRama());
		}
		
		return "nueva-titulacion";
	}
	
	
	/** Acceso a Titulacion **/
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String titulacion(Model model, @PathVariable long id) {
		
		this.titulacion = titulaciones.findOne(id);
		
		if(titulacion != null) {
			model.addAttribute("nombre", titulacion.getNombre());
			model.addAttribute("rama", titulacion.getRama());
			model.addAttribute("asignaturas", titulacion.getAsignaturas());
			return "titulacion";
		}
		
		return "404";
	}
	
	
	/** Borrar Titulacion **/
	
	@RequestMapping(value="/borrar/{id}", method=RequestMethod.GET)
	public String borrarTitulacion(Model model, @PathVariable long id) {
		model.addAttribute("nombre", titulaciones.findOne(id).getNombre());
		model.addAttribute("back", "/root");
		titulaciones.delete(id);
		return "eliminado"; 
	}
	
}
