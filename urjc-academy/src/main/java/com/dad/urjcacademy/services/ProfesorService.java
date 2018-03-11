package com.dad.urjcacademy.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.entity.Profesor;
import com.dad.urjcacademy.repository.ProfesorRepository;

@Component
public class ProfesorService {

	@Autowired
	private ProfesorRepository repository;
	
	/*Select*/
	public Profesor findById(long id) {
		return repository.findOne(id);
	}
	
	public Profesor findByTlf(String tlf) {
		return repository.findByTlf(tlf);
	}
	
	public List<Profesor> findAll() {
		return repository.findAll();
	}
	
	public List<Profesor> getAllByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	public List<Profesor> getAllByApellido(String apellido) {
		return repository.findByApellido(apellido);
	}
	
	/*Insert -> Operaciones para usar con AdminController*/
	public Profesor save(Profesor profesor) {
		return repository.save(profesor);
	}
	
	/*Delete -> Operaciones para usar con AdminController*/
	
	public void delete(long id) {
		repository.delete(id);
	}
	
	public void delete(Profesor profesor) {
		repository.delete(profesor);
	}
	
	public Profesor deleteByLogin(String login) {
		return repository.deleteByLogin(login);
	}
	
	/*Asignatura -> Operaciones para Usar con AdminController*/
	
	public boolean asociarAsignaturaProfesor(Profesor profesor, Asignatura asignatura) {
		if(profesor.asignarAsignatura(asignatura)) {
			return this.save(profesor) != null;
		}
		return false;
	}
	
	public boolean quitarAsignaturaProfesor(Profesor profesor, Asignatura asignatura) {
		if(profesor.quitarAsignatura(asignatura)) {
			return this.save(profesor) != null;
		}
		return false;
	}
	
	
}
