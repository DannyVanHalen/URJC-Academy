package com.dad.urjcacademy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.repository.AsignaturaRepository;

@Component
public class AsignaturaService {

	@Autowired
	private AsignaturaRepository repository;
	
	
	public Asignatura findById(long id) {
		return repository.findOne(id);
	}
	
	public Asignatura save(Asignatura asignatura) {
		return this.save(asignatura);
	}
	
	public void deleteId(long id) {
		repository.delete(id);
	}
	
	public void delete(Asignatura asignatura) {
		repository.delete(asignatura);
	}
	
	/*Profesores*/
	
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
	
	
	/*Alumnos*/
	
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
	
}
