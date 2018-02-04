package com.dad.urjcacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Asignatura;


public interface AsignaturaRepository extends JpaRepository<Asignatura,Long>{

	Asignatura findByNombre(String nombre);
	
}
