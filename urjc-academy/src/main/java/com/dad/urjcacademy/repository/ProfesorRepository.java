package com.dad.urjcacademy.repository;


import com.dad.urjcacademy.entity.Profesor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

	/** SELECT **/
	
	Profesor findByTlf(String tlf);
	List<Profesor> findByNombre(String nombre);
	List<Profesor> findByApellido(String apellido);
	Profesor findByNombreAndApellido(String nombre, String apellido);
	
	/** DELETE **/
	Profesor deleteByLogin(String login);
	
}
