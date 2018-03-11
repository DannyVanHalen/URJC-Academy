package com.dad.urjcacademy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Alumno;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.AlumnoRepository;

@Component
public class AlumnoService extends UsuarioService {

	@Autowired
	private AlumnoRepository repository;
	
/*Select*/
	
	public Alumno findById(long id) {
		return repository.findOne(id);
	}
	
	public Alumno findByLogin(String login) {
		return (Alumno) super.findByLogin(login);
	}
	
	public Alumno findByMaiLogin(String maiLogin) {
		return (Alumno) super.findByMaiLogin(maiLogin);
	}
	
	public Alumno findByTlf(String tlf) {
		return repository.findByTlf(tlf);
	}
	
	
	public List<Usuario> findAll() {
		return  super.findByRol("alumno");
	}
	
	public List<Alumno> findAllByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	public List<Alumno> findByApellido(String apellido) {
		return repository.findByApellido(apellido);
	}
	
	/*Insert*/
	
	public Alumno save(Alumno alumno) {
		return repository.save(alumno);
	}
	
	/*Delete*/
	
	public void delete(long id) {
		repository.delete(id);
	}
	
	public void delete(Alumno alumno) {
		repository.delete(alumno);
	}
	
	/*Asignatura*/
	
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
	
}
