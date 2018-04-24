package com.dad.urjcacademy.repository;

import java.util.List;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Asignatura;

@CacheConfig(cacheNames="asignaturas")
public interface AsignaturaRepository extends JpaRepository<Asignatura,Long>{
	
	@CacheEvict(allEntries=true)
	Asignatura save(Asignatura asignatura);
	
	@CacheEvict(allEntries=true)
	void delete(long id);
	
	@CacheEvict(allEntries=true)
	void delete(Asignatura asignatura);
	
	@Cacheable
	Asignatura findByNombre(String nombre);
	
	@Cacheable
	Asignatura findOne(long id);
	
	@Cacheable
	List<Asignatura> findAll();
	
}
