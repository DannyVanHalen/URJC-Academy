package com.dad.urjcacademy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.services.ProfesorService;
import com.dad.urjcacademy.entity.Profesor;


@RestController
@RequestMapping("/teachers")
public class ProfesorController extends UsuarioController {
	
	@Autowired
	private ProfesorService profesores;
	
	
	
		
	//Obtener todos los profesores registrados
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Profesor>> getAllProfesores() {
		return new ResponseEntity<List<Profesor>>(profesores.findAll(),HttpStatus.OK);
	}
	
	// Obtener Profesor
	@GetMapping(value="/{id}")
	public ResponseEntity<Profesor> getProfesor(@PathVariable long id) {
		
		if(profesores.exists(id)) {
			return new ResponseEntity<>(profesores.findById(id),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	//Modificar Alumno
	@PutMapping(value="/{id}")
	public ResponseEntity<Profesor> updateProfesor(@PathVariable long id,
			@RequestBody Profesor update) {
		
		if(profesores.exists(id)) {
			Profesor usuario = profesores.findById(id);
			usuario.setLogin(update.getLogin());
			usuario.setMaiLogin(update.getMaiLogin());
			usuario.setPass(update.getPass());
			usuario.setRol(update.getRol());
			usuario.setNombre(update.getNombre());
			usuario.setApellido(update.getApellido());
			usuario.setTlf(update.getTlf());
			usuario.setAsignaturas(update.getAsignaturas());
			usuario.setTutoriasProfesor(update.getTutoriasProfesor());
			return new ResponseEntity<>(profesores.save(usuario),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		
	}
	
}
