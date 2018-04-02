package com.dad.urjcacademy.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.services.ApuntesService;

@RestController
@RequestMapping("/apuntes")
public class ApuntesController {

	@Autowired
	private ApuntesService apuntes;
	

	//Obtener todos apuntes
	@GetMapping(value="")
	public ResponseEntity<List<Apuntes>> getAllApuntes() {
		return new ResponseEntity<>(apuntes.findAll(),HttpStatus.OK);
	}
	
	//Obtener apuntes
	@GetMapping(value="/{id}")
	public ResponseEntity<Apuntes> getApuntes(@PathVariable long id) {
		
		if(apuntes.exists(id)) {
			return new ResponseEntity<>(apuntes.findById(id),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<Apuntes> addApuntes(@RequestBody Apuntes apuntes) {
		return new ResponseEntity<>(this.apuntes.save(apuntes),HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}/update")
	public ResponseEntity<Apuntes> updateApuntes(@PathVariable long id,
			@RequestBody Apuntes update) {
		
		if(apuntes.exists(id)) {
			Apuntes apuntes = this.apuntes.findById(id);
			apuntes.setLinkApuntes(update.getLinkApuntes());
			apuntes.setTema(apuntes.getTema());
			return new ResponseEntity<>(this.apuntes.save(apuntes),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping(value="/{id}/delete")
	public ResponseEntity<Apuntes> deleteApuntes(@PathVariable long id) {
		
		if(apuntes.exists(id)) {
			Apuntes apuntes = this.apuntes.findById(id);
			this.apuntes.delete(id);
			return new ResponseEntity<>(apuntes,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
}
