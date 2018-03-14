package com.dad.urjcacademy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.AlumnoRepository;

@Component
public class AlumnoService  {

	@Autowired
	private AlumnoRepository repository;
	
/*Select*/
	
	public Alumno findById(long id) {
		return repository.findOne(id);
	}
	
	
	public Alumno findByTlf(String tlf) {
		return repository.findByTlf(tlf);
	}
	
	
	public List<Alumno> findAll() {
		return  repository.findAll();
	}
	
	public List<Alumno> findAllByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	public List<Alumno> findByApellido(String apellido) {
		return repository.findByApellido(apellido);
	}
	
	/*Exists*/
	public boolean exists(long id) {
		return repository.exists(id);
	}
	
	/*Insert -> Operaciones para usar con AdminController*/
	
	public Alumno save(Alumno alumno) {
		return repository.save(alumno);
	}
	
	/*Delete -> Operaciones para usar con AdminController*/
	
	public void delete(long id) {
		repository.delete(id);
	}
	
	public void delete(Alumno alumno) {
		repository.delete(alumno);
	}
	
	/*Asignatura -> Operaciones para usar con AdminController*/
	
	public boolean matricularAlumnoAsignatura(Alumno alumno, Asignatura asignatura) {
		if(alumno.matricularAsignatura(asignatura)) {
			return this.save(alumno) != null;
		}
		return false;
	}
	
	public boolean desmatricularAlumnoAsignatura(Alumno alumno, Asignatura asignatura) {
		if(alumno.desmatricularAsignatura(asignatura)) {
			return this.save(alumno) != null;
		}
		return false;
	}
	
	public boolean desmatricularAlumnosAsignatura(Asignatura asignatura) {
		for(Alumno alumno: repository.findAll()) {
			if(alumno.getAsignaturasAlumno().contains(asignatura))
				this.desmatricularAlumnoAsignatura(alumno, asignatura);
		}
		return true;
	}
	
	public boolean desmatricularAlumnosTodasAsignaturas(Alumno alumno) {
		if(alumno.desmatircularTodasAsignaturas()) {
			return this.save(alumno) != null;
		}
		return false;
	}
	
	/*Tutoria -> Operaciones para usar por AdminController y AlumnoController*/
	
	public boolean asociarTutoriaAlumno(Alumno alumno, Tutoria tutoria) {
		if(alumno.asignarTutoria(tutoria)) {
			return this.save(alumno) != null;
		}
		return false;
	}
	
	public boolean eliminarTutoriaAlumno(Alumno alumno, Tutoria tutoria) {
		if(alumno.eliminarTutoria(tutoria)) {
			return this.save(alumno) != null;
		}
		return false;
	}
	
	public boolean eliminarTodasTutoriasAlumno(Alumno alumno) {
		if(alumno.eliminarTodasTutorias()) {
			return this.save(alumno) != null;
		}
		return false;
	}
	
}
