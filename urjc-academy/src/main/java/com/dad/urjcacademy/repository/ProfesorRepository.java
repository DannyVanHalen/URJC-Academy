package com.dad.urjcacademy.repository;

<<<<<<< HEAD
import com.dad.urjcacademy.entity.Profesor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

	/** SELECT **/
	
	Profesor findByTlf(String tlf);
	Profesor findByLogin(String login);
=======

import com.dad.urjcacademy.entity.Profesor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

	/** SELECT **/
	
	Profesor findByTlf(String tlf);
>>>>>>> refs/remotes/origin/develop
	List<Profesor> findByNombre(String nombre);
	List<Profesor> findByApellido(String apellido);
	Profesor findByNombreAndApellido(String nombre, String apellido);
	
	/** DELETE **/
	Profesor deleteByLogin(String login);
	
}
