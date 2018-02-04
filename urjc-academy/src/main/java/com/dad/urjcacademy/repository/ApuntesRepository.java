package com.dad.urjcacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;


public interface ApuntesRepository extends JpaRepository<Apuntes, Long>{

	List<Apuntes> findByAsignatura(Asignatura asignaturas);
	
}
