package com.dad.urjcacademy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;
import com.dad.urjcacademy.repository.ApuntesRepository;

@Component
public class ApuntesService {

	@Autowired
	private ApuntesRepository repository;

	/*Select*/
	
	public Apuntes findById(long id) {
		return repository.findOne(id);
	}
	
	public Apuntes findByAsignaturaAndTema(Asignatura asignatura, String tema) {
		return repository.findByAsignaturaAndTema(asignatura, tema);
	}
	
	public List<Apuntes> findAll() {
		return repository.findAll();
	}
	
	/*Insert*/
	public Apuntes save(Apuntes apuntes) {
		return repository.save(apuntes);
	}
	
	/*Delete*/
	public void delete(long id) {
		repository.delete(id);
	}
	
	public void delete(Apuntes apuntes) {
		repository.delete(apuntes);
	}
	
	public void quitarApuntesAsignatura(Asignatura asignatura) {
		for(Apuntes apuntes: asignatura.getApuntesAsignatura()) {
			repository.delete(apuntes.getId());
		}
	}
	
	
	/*Exists*/
	public boolean exists(long id) {
		return repository.exists(id);
	}
	
}
