package com.dad.urjcacademy.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dad.urjcacademy.entity.Apuntes;
import com.dad.urjcacademy.entity.Asignatura;
//import com.dad.urjcacademy.entity.Asignatura;

@CacheConfig(cacheNames="asignaturas")
public interface ApuntesRepository extends JpaRepository<Apuntes, Long>{

	/**INSERT**/
	@CacheEvict(allEntries=true)
	Apuntes save(Apuntes apuntes);
	
	/**SELECT**/
	@Cacheable
	Apuntes findByTema(String tema);
	@Cacheable
	Apuntes findByAsignaturaAndTema(Asignatura asignatura, String tema);
	
	@Query("SELECT apuntes FROM Apuntes apuntes ORDER BY apuntes.asignatura ASC")
	List<Apuntes> findByAsignatura(Asignatura asignatura);
	
	@CacheEvict(allEntries=true)
	void delete(long id);
	
	@CacheEvict(allEntries=true)
	void delete(Apuntes apuntes);
	
}
