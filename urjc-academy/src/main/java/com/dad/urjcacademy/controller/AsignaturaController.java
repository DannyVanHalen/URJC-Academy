package com.dad.urjcacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.services.AsignaturaService;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

	@Autowired
	private AsignaturaService asignaturas;
	
	
	// Obetener todas las asignaturas
	@GetMapping(value="")
	public ResponseEntity<List<Asignatura>> getAllAsignaturas() {
		return new ResponseEntity<>(asignaturas.findAll(),HttpStatus.OK);
	}
	
	
	// Obener una Asinatura
	@GetMapping(value="/{id}")
	public ResponseEntity<Asignatura> getAsignatura(@PathVariable long id) {
		
		if(asignaturas.exists(id) ) {
			return new ResponseEntity<>(asignaturas.findById(id),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//Agregar Asignatura
	@PostMapping(value="/nueva")
	public ResponseEntity<Asignatura> newAsignatura(@RequestBody Asignatura asignatura) {
		return new ResponseEntity<>(asignaturas.save(asignatura),HttpStatus.CREATED);
	}
	
	//ModificarAsignatura
	//Agregar Profesor
	@PutMapping(value="/{id}/add-profesor")
	public ResponseEntity<Asignatura> addProfesor(@PathVariable long id,
			@RequestBody Profesor profesor) {
		
		if(asignaturas.exists(id)) {
			Asignatura asignatura = asignaturas.findById(id);
			if(asignaturas.asociarProfesorAsignatura(asignatura, profesor))
				return new ResponseEntity<>(asignatura,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//Agregar varios profesores
	@PutMapping(value="/{id}/add-profesores")
	public ResponseEntity<Asignatura> addProfesores(@PathVariable long id, 
			@RequestBody List<Profesor> profesores) {
		
		if(asignaturas.exists(id)) {
			Asignatura asignatura = asignaturas.findById(id);
			asignatura.setProfesores(profesores);
			return new ResponseEntity<>(asignatura,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//Sustituir Profesor
	@PutMapping(value="/{id}/replace-profesor")
	public ResponseEntity<Asignatura> replaceProfesor(@PathVariable long id,
			@RequestBody Profesor oldProfesor, @RequestBody Profesor newProfesor ) {
		
		
		if(asignaturas.exists(id)) {
			
			Asignatura asignatura = asignaturas.findById(id);
			asignaturas.desasociarProfesorAsignatura(asignatura, oldProfesor);
			asignaturas.asociarProfesorAsignatura(asignatura, newProfesor);
			return new ResponseEntity<>(asignatura, HttpStatus.ACCEPTED);
			
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	
	
	
}
