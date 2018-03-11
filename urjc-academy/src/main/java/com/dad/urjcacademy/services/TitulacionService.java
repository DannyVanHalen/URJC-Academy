package com.dad.urjcacademy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Titulacion;
import com.dad.urjcacademy.repository.TitulacionRepository;

@Component
public class TitulacionService {

	@Autowired
	private TitulacionRepository repository;
	
	/*Select*/
	
	public Titulacion findById(long id) {
		return repository.findOne(id);
	}
	
	public Titulacion findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	public List<Titulacion> findByRama(String rama) {
		return repository.findByRama(rama);
	}
	
	/*Insert*/
	
	public Titulacion save(Titulacion titulacion) {
		return repository.save(titulacion);
	}
	
	/*Delete*/
	
	public void delete(long id) {
		repository.delete(id);
	}
	
	public void delete(Titulacion titulacion) {
		repository.delete(titulacion);
	}
	
}
