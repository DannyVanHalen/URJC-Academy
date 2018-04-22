package com.dad.urjcacademy.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.dad.urjcacademy.entity.Usuario;

//@CacheConfig(cacheNames="usuarios")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	/** Select **/
	Usuario findById(long id);
	Usuario findByLogin(String login);
	Usuario findByLoginAndPass(String login, String pass);
	Usuario findByMaiLogin(String maiLogin);
	
	List<Usuario> findByRol(String rol);
	//List<Usuario> findByRoles(String roles);
	

	
	/** Delete **/
	Usuario deleteByMaiLogin(String maiLogin);
	
	
	/**Insert**/
	Usuario save(Usuario usuario);
	
	
}
