package com.dad.urjcacademy.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dad.urjcacademy.repository.ApuntesRepository;

@Controller
@RequestMapping("/apuntes")
public class ApuntesController {

	@Autowired
	private ApuntesRepository apuntes;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String subir_contenido(Model model, HttpServletRequest request) {
			return "subir-contenido";
	}
	
	/**
	@RequestMapping(value="/volver-atras", method=RequestMethod.GET)
	public String volver_atras(Model model, HttpServletRequest request) {
		return "asignatura";
	}**/
	
	
	

	
	
}
