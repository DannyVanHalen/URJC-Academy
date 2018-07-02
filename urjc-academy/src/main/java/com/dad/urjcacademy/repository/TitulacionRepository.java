package com.dad.urjcacademy.repository;

import com.dad.urjcacademy.entity.Titulacion;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@CacheConfig(cacheNames="titulaciones")
public interface TitulacionRepository extends JpaRepository<Titulacion, Long> {

	/**INSERT**/
	@CacheEvict(allEntries=true)
	Titulacion save(Titulacion titulacion);
	
	/** SELECT **/
	@Cacheable
	Titulacion findOne(long id);
	@Cacheable
	Titulacion findByNombre(String nombre);
	@Cacheable
	List<Titulacion> findAll();
	
	@Query("SELECT titulacion.nombre FROM Titulacion titulacion WHERE titulacion.rama = ?1 ORDER BY titulacion.nombre ASC")
	List<Titulacion> findByRamaASC(String rama);
	
	/** DELETE **/
	@CacheEvict(allEntries=true)
	void delete(long id);
	@CacheEvict(allEntries=true)
	void delete(Titulacion titualicon);
}
