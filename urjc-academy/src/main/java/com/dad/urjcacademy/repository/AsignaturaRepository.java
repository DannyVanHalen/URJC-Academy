package com.dad.urjcacademy.repository;

import java.util.List;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Asignatura;

public interface AsignaturaRepository extends JpaRepository<Asignatura,Long>{
	
	Asignatura save(Asignatura asignatura);
	
	Asignatura findByNombre(String nombre);
	
	Asignatura findOne(long id);
	
	List<Asignatura> findAll();
	
}
