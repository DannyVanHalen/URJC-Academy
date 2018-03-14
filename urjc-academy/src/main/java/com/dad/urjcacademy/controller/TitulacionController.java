package com.dad.urjcacademy.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.services.TitulacionService;
import java.util.List;

@RestController
@RequestMapping("/titulacion")
public class TitulacionController {

	@Autowired
	private TitulacionService titulaciones;
	
	
	// Obtener todas las titulaciones
	@GetMapping(value="")
	public ResponseEntity<List<Titulacion>> getAllTitulaciones() {
		return new ResponseEntity<>(titulaciones.findAll(),HttpStatus.OK);
	}
	
	// Obtener una titulacion 
	@GetMapping(value="{id}")
	public ResponseEntity<Titulacion> getTitulacion(@PathVariable long id) {
		
		if(titulaciones.exists(id)) {
			return new ResponseEntity<>(titulaciones.findById(id),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	// Modificar 
	@PutMapping(value="{id}")
	public ResponseEntity<Titulacion> updateTitulacion(@PathVariable long id,
			@RequestBody Titulacion update) {
		
		if(titulaciones.exists(id)) {
			Titulacion titulacion = titulaciones.findById(id);
			titulacion.setNombre(update.getNombre());
			titulacion.setRama(update.getRama());
			titulacion.setAsignaturas(update.getAsignaturas());
			return new ResponseEntity<>(titulaciones.save(titulacion),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	@PutMapping(value="{id}/agregar-asignatura")
	public ResponseEntity<Titulacion> addAsignatura(@PathVariable long id,
			@RequestBody Asignatura asignatura) {
		if(titulaciones.exists(id)) {
			Titulacion titulacion = titulaciones.findById(id);
			if(titulaciones.asignarAsignaturaTitulacion(titulacion, asignatura))
				return new ResponseEntity<>(titulacion,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Titulacion> deleteTitulacion(@PathVariable long id) {
		
		if(titulaciones.exists(id)) {
			titulaciones.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	
	

	
	
}
