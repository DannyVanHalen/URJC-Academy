package com.dad.urjcacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.services.TutoriaService;

@RestController
@RequestMapping("/tutorial")
public class TutoriaController {

	@Autowired
	private TutoriaService tutorias;
	
	// Obtener todas las tutorias disponibles
	@GetMapping(value="/all")
	public ResponseEntity<List<Tutoria>> getAllTutoria() {
		return new ResponseEntity<>(tutorias.findAll(),HttpStatus.OK);
	}
	
	// Obtener tutoria
	@GetMapping(value="/{id}")
	public ResponseEntity<Tutoria> getTutoria(@PathVariable long id) {
		
		if(tutorias.exists(id)) {
			return new ResponseEntity<>(tutorias.findById(id),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	// Agregar Tutoria
	@PostMapping(value="/new-tutorial")
	public ResponseEntity<Tutoria> newTutoria(@RequestBody Tutoria tutoria) {
		return new ResponseEntity<>(tutorias.save(tutoria),HttpStatus.CREATED);
	}
	
	
	// Modificar Tutoria
	@PutMapping(value="/{id}/update")
	public ResponseEntity<Tutoria> updateTutoria(@PathVariable long id, 
			@RequestBody Tutoria update) {
		
		if(tutorias.exists(id)) {
			Tutoria tutoria = tutorias.findById(id);
			tutoria.setDayOfMonth(update.getDayOfMonth());
			tutoria.setMonth(update.getMonth());
			tutoria.setYear(update.getYear());
			tutoria.setHour(update.getHour());
			tutoria.setMin(update.getMin());
			tutoria.setValidate(update.getValidate());
			return new ResponseEntity<Tutoria>(tutorias.save(tutoria),HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<Tutoria>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping(value="/{id}/delete")
	public ResponseEntity<Tutoria> deleteTutoria(@PathVariable long id) {
		
		if(tutorias.exists(id)) {
			Tutoria tutoria = tutorias.findById(id);
			tutorias.delete(id);
			return new ResponseEntity<>(tutoria,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
}
