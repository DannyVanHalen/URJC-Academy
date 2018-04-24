package com.dad.urjcacademy.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.services.ApuntesService;
import com.dad.urjcacademy.services.AsignaturaService;

@Controller
@RequestMapping("/asignatura")
public class AsignaturaController {

	@Autowired
	private AsignaturaService asignaturas;
	@Autowired
	private ApuntesService apuntes;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//@Cacheable(cacheNames="asignaturas")
	public String asignatura(Model model, @PathVariable long id,
			HttpServletRequest request) {
		
		Asignatura asignatura = asignaturas.findById(id);
		
		if(asignatura != null) {
			model.addAttribute("asignatura", asignatura.getNombre());
			//Agregamos contenido
			model.addAttribute("apuntes", asignatura.getApuntesAsignatura());
			//El usuario es profesor
			model.addAttribute("profesor", request.isUserInRole("ROLE_TEACHER"));
			
			return "asignatura";
		}
		
		return "404";
	}
	
	@RequestMapping(value="/volver-atras", method=RequestMethod.GET)
	public String volver_atras(Model model, HttpServletRequest request) {
		
		if(request.isUserInRole("ROLE_TEACHER"))
			return "redirect:/profesor";
		if(request.isUserInRole("ROLE_STUDENT"))
			return "redirect:/alumno";
		
		return "505";
		
	}
	
	/** Agregar Apuntes **/
	@RequestMapping(value="/{id}/agregar-contenido", method=RequestMethod.GET)
	public String subir_contenido(Model model, HttpServletRequest request,
			@PathVariable long id) {
			return "subir-contenido";
	}
	
	/**
	 * Subir Apuntes
	 * 
	 * Sube contenido al directorio de google Drive de urjc.academy.root@gmail.com
	 * Copia el enlace de la subida 
	 * Publica el contenido en la aplicacion
	 * 
	 */
	@RequestMapping(value="/{id}/subir-contenido", method=RequestMethod.POST)
	//@CacheEvict(cacheNames="asignaturas")
	public String subir_contenido(Model model, HttpServletRequest request,
			@RequestParam String tema, @RequestParam String linkApuntes, 
			@PathVariable long id) {
		
		Asignatura asignatura = asignaturas.findById(id);
		
		if(asignatura != null) {
			Apuntes ok = apuntes.save(new Apuntes(tema,linkApuntes,asignatura));
			if(ok != null) {
				asignaturas.subirApuntesAsignatura(asignatura, ok);
				return "redirect:/asignatura/{id}";
			}
		}
		
		return "505";
		
	}
	
	
}
