package com.dad.urjcacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Alumno;


public interface AlumnoRepository extends JpaRepository<Alumno,Long>{

	/** SELECT **/
	
	Alumno findByTlf(String tlf);
	List<Alumno> findByNombre(String nombre);
	List<Alumno> findByApellido(String apellido);
	Alumno findByNombreAndApellido(String nombre, String apellido);
	
	/** DELETE **/
	Alumno deleteByLogin(String login);
}
