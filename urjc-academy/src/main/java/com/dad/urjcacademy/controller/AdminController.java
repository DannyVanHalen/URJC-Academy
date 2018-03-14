package com.dad.urjcacademy.controller;

import java.util.List;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Usuario;
//import com.dad.urjcacademy.generator.PassGenerator;
//import com.dad.urjcacademy.repository.ProfesorRepository;
import com.dad.urjcacademy.services.UsuarioService;

@RestController
@RequestMapping("/root")
public class AdminController extends UsuarioController{
	
	@Autowired
	private UsuarioService usuarios;
	
	/*Root*/
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<Usuario> root() {
		
		Usuario usuario = usuarios.findByLogin("root");
		if(usuarios.exists(usuario.getId())) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	/*Gestión de usuarios*/
	
	// Nuevo Profesor
	@PostMapping(value="/add-teacher")
	public ResponseEntity<Usuario> newProfesor(@RequestBody Profesor profesor) {
		return new ResponseEntity<>(usuarios.save(profesor),HttpStatus.CREATED);
	}
	
	@PostMapping(value="/add-student")
	public ResponseEntity<Usuario> newAlumno(@RequestBody Alumno alumno) {
		return new ResponseEntity<>(usuarios.save(alumno),HttpStatus.CREATED);
	}
	
	// Todos los usuarios registrados
	@GetMapping(value="/users")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		return new ResponseEntity<>(usuarios.findAll(),HttpStatus.OK);
	}
	
	//Buscar Usuario
	@GetMapping(value="/users/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable long id) {
		
		if(usuarios.exists(id)) {
			return new ResponseEntity<>(usuarios.findById(id),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	}
	
	// Modificar Usuario
	@PutMapping(value="/user/{id}/update")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable long id,
			@RequestBody Usuario update) {
		
		if(usuarios.exists(id)) {
			Usuario usuario = usuarios.findById(id);
			usuario.setLogin(update.getLogin());
			usuario.setMaiLogin(update.getMaiLogin());
			usuario.setPass(update.getPass());
			usuario.setRol(update.getRol());
			return new ResponseEntity<>(usuarios.save(usuario),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	
	}
	
	// Borrado Usuarios
	@DeleteMapping(value="/user/{id}/delete")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable long id) {
		
		if(usuarios.exists(id)) {
			usuarios.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	}
	
	/*Gestión Asignaturas*/
	
	
	
	
	
}
