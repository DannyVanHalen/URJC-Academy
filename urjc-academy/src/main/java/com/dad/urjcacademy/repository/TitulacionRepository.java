package com.dad.urjcacademy.repository;

import com.dad.urjcacademy.entity.Titulacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TitulacionRepository extends JpaRepository<Titulacion, Long> {

	/** SELECT **/
	
	Titulacion findByNombre(String nombre);
	List<Titulacion> findByRama(String rama);
	
	/** DELETE **/
	
}
