package com.dad.urjcacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.services.AlumnoService;
//import com.dad.urjcacademy.entity.Usuario;
//import com.dad.urjcacademy.repository.AlumnoRepository;
//import com.dad.urjcacademy.repository.UsuarioRepository;

@RestController
@RequestMapping("/students")
public class AlumnoController extends UsuarioController {

	@Autowired
	private AlumnoService alumnos;
	
	// Obtener todos Alumnos Registrados
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Alumno>> getAllAlumnos() {
		return new ResponseEntity<List<Alumno>>(alumnos.findAll(),HttpStatus.OK);
	}
	
	//Obtener Alumno
	@GetMapping(value="/{id}")
	public ResponseEntity<Alumno> getAlumno(@PathVariable long id) {
		if(alumnos.exists(id)) {
			return new ResponseEntity<>(alumnos.findById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//Modificar Alumno
	@PutMapping(value="/{id}")
	public ResponseEntity<Alumno> updateAlumno(@PathVariable long id, 
			@RequestBody Alumno update) {
		
		if(alumnos.exists(id)) {
			Alumno usuario = alumnos.findById(id);
			usuario.setLogin(update.getLogin());
			usuario.setMaiLogin(update.getMaiLogin());
			usuario.setPass(update.getPass());
			usuario.setRol(update.getRol());
			usuario.setNombre(update.getNombre());
			usuario.setApellido(update.getApellido());
			usuario.setTlf(update.getTlf());
			usuario.setAsignaturasAlumno(update.getAsignaturasAlumno());
			usuario.setTutoriasAlumno(update.getTutoriasAlumno());
			return new ResponseEntity<>(alumnos.save(usuario),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		
	}
	
}
