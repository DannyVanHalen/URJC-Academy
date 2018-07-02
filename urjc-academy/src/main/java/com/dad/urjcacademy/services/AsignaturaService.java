package com.dad.urjcacademy.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.repository.AsignaturaRepository;

@Component
public class AsignaturaService {

	@Autowired
	private AsignaturaRepository repository;
	
	
	public Asignatura findById(long id) {
		return repository.findOne(id);
	}
	
	public List<Asignatura> findAll() {
		return repository.findAll();
	}
	
	public Asignatura save(Asignatura asignatura) {
		return repository.save(asignatura);
	}
	
	public void deleteId(long id) {
		repository.delete(id);
	}
	
	public void delete(Asignatura asignatura) {
		repository.delete(asignatura);
	}
	
	/*Exists*/
	public boolean exists(long id) {
		return repository.exists(id);
	}
	
	/*Profesores -> Operaciones para usar con AdminController*/
	
	public boolean asociarProfesorAsignatura(Asignatura asignatura, Profesor profesor) {
		if(asignatura.asociaProfesor(profesor)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean desasociarProfesorAsignatura(Asignatura asignatura, Profesor profesor) {
		if(asignatura.desasociaProfesor(profesor)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean asociarProfesoresAsignatura(Asignatura asignatura, List<Profesor> profesores) {
		if(asignatura.asociarProfesores(profesores)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean desasociarProfesoresAsignatura(Asignatura asignatura, List<Profesor> profesores) {
		if(asignatura.desasociarProfesores(profesores)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean desasociarTodosProfesores(Asignatura asignatura) {
		if(asignatura.desasociarATodos()) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	
	/*Alumnos -> Operaciones para usar con AdminController*/
	
	public boolean matircularAlumnoAsignatura(Asignatura asignatura, Alumno alumno) {
		if(asignatura.matircularAlumno(alumno)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean desmatricularAlumnoAsignatura(Asignatura asignatura, Alumno alumno) {
		if(asignatura.desmatricularAlumno(alumno)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean matricularAlumnosAsignatura(Asignatura asignatura, List<Alumno> alumnos) {
		if(asignatura.matricularAlumnos(alumnos)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean desmatricularAlumnosAsignatura(Asignatura asignatura, List<Alumno> alumnos) {
		if(asignatura.desmatricularAlumnos(alumnos)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean desmatricularTodosAlumnos(Asignatura asignatura) {
		if(asignatura.desasociarATodos()) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	/*Tutorias -> Operaciones usadas por ProfesorController (verifica) y AdminController (borrado). 
	 * Sería conveniente intentar automatizar los borrados después que cada tutoría haya tenido lugar
	 * en la fecha y hora indicada.*/
	
	public boolean verificaTutoria(Asignatura asignatura, Tutoria tutoria) {
		if(asignatura.agregarTutoria(tutoria)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean borraTutoria(Asignatura asignatura, Tutoria tutoria) {
		if(asignatura.borrarTutoria(tutoria)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean borraTodasTutoriasAsociadas(Asignatura asignatura) {
		if(asignatura.borrarTodasTutorias()) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	/*Apuntes -> Operaciones usadas por ProfesorController*/
	
	public boolean subirApuntesAsignatura(Asignatura asignatura, Apuntes apuntes) {
		if(asignatura.subirApuntes(apuntes)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean borraApuntesAsignatura(Asignatura asignatura, Apuntes apuntes) {
		if(asignatura.eliminarApuntes(apuntes)) {
			return this.save(asignatura) != null;
		}
		return false;
	}
	
	public boolean borraTodosApuntesAsignatura(Asignatura asignatura) {
		if(asignatura.borrarTodosApuntes()) {
			return this.save(asignatura)  != null;
		}
		return false;
	}
	
}
