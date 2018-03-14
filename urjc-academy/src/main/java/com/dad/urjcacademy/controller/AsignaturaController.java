package com.dad.urjcacademy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.services.AsignaturaService;
import com.dad.urjcacademy.services.UsuarioService;

@RestController
@RequestMapping("/subjects")
public class AsignaturaController {

	@Autowired
	private AsignaturaService asignaturas;
	
	@Autowired
	private UsuarioService usuarios;
	
	
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
	@PostMapping(value="/new-subject")
	public ResponseEntity<Asignatura> newAsignatura(@RequestBody Asignatura asignatura) {
		return new ResponseEntity<>(asignaturas.save(asignatura),HttpStatus.CREATED);
	}
	
	//ModificarAsignatura
	//Agregar Profesor
	@PutMapping(value="/{id}/add-teacher")
	public ResponseEntity<Asignatura> addProfesor(@PathVariable long id,
			@RequestBody Profesor profesor) {
		
		if(asignaturas.exists(id)) {
			Asignatura asignatura = asignaturas.findById(id);
			if(asignaturas.asociarProfesorAsignatura(asignatura, profesor)) {
				profesor.asignarAsignatura(asignatura);
				usuarios.save(profesor);
				return new ResponseEntity<>(asignatura,HttpStatus.OK);
			}
				
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//Agregar varios profesores
	@PutMapping(value="/{id}/add-teachers")
	public ResponseEntity<Asignatura> addProfesores(@PathVariable long id, 
			@RequestBody List<Profesor> profesores) {
		
		if(asignaturas.exists(id)) {
			Asignatura asignatura = asignaturas.findById(id);
			asignatura.setProfesores(profesores);
			for(Profesor profesor: profesores) {
				profesor.asignarAsignatura(asignatura);
				usuarios.save(profesor);
			}
			return new ResponseEntity<>(asignatura,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//Sustituir Profesor
	@PutMapping(value="/{id}/replace-teacher")
	public ResponseEntity<Asignatura> replaceProfesor(@PathVariable long id,
			@RequestBody Profesor oldProfesor, @RequestBody Profesor newProfesor ) {
		
		
		if(asignaturas.exists(id)) {
			
			Asignatura asignatura = asignaturas.findById(id);
			if(oldProfesor.estaAsignadaAsignatura(asignatura)) {
				asignaturas.desasociarProfesorAsignatura(asignatura, oldProfesor);
				asignaturas.asociarProfesorAsignatura(asignatura, newProfesor);
				oldProfesor.quitarAsignatura(asignatura);
				newProfesor.asignarAsignatura(asignatura);
				usuarios.save(oldProfesor);
				usuarios.save(newProfesor);
				return new ResponseEntity<>(asignatura, HttpStatus.ACCEPTED);
			} 
			
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	


	//Agregar alumo
	@PutMapping(value="/{id}/add-student")
	public ResponseEntity<Asignatura> addAlumno(@PathVariable long id,
			@RequestBody Alumno alumno) {
		
		if(asignaturas.exists(id)) {
			Asignatura asignatura = asignaturas.findById(id);
			alumno.matricularAsignatura(asignatura);
			asignaturas.matircularAlumnoAsignatura(asignatura, alumno);
			return new ResponseEntity<>(asignatura,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	//Agregar alumnos
	@PutMapping(value="/{id}/add-students")
	public ResponseEntity<Asignatura> addAlumnos(@PathVariable long id, 
			@RequestBody List<Alumno> alumnos) {
		
		if(asignaturas.exists(id)) {
			Asignatura asignatura = asignaturas.findById(id);
			for(Alumno alumno: alumnos) {
				alumno.matricularAsignatura(asignatura);
				usuarios.save(alumno);
			}
			asignaturas.matricularAlumnosAsignatura(asignatura, alumnos);
			return new ResponseEntity<>(asignatura,HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping(value="/{id}/delete")
	public ResponseEntity<Asignatura> deleteAsignatura(@PathVariable long id) {
		
		if(asignaturas.exists(id)) {
			Asignatura asignatura = asignaturas.findById(id);
			for(Profesor profesor: asignatura.getProfesores()) {
				profesor.quitarAsignatura(asignatura);
				usuarios.save(profesor);
			}
			for(Alumno alumno: asignatura.getAlumnos()) {
				alumno.desmatricularAsignatura(asignatura);
				usuarios.save(alumno);
			}
			asignaturas.desasociarProfesoresAsignatura(asignatura, asignatura.getProfesores());
			asignaturas.desmatricularAlumnosAsignatura(asignatura, asignatura.getAlumnos());
			asignaturas.deleteId(id);
			return new ResponseEntity<>(asignatura,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	
	
	
}
