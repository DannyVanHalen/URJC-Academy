package com.dad.urjcacademy.repository;

import java.util.List;



import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.dad.urjcacademy.entity.Usuario;

@Cacheable(cacheNames="usuarios")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	/** Select **/
	@Cacheable
	Usuario findById(long id);
	@Cacheable
	Usuario findByLogin(String login);
	@Cacheable
	Usuario findByLoginAndPass(String login, String pass);
	@Cacheable
	Usuario findByMaiLogin(String maiLogin);
	
	@Cacheable
	List<Usuario> findByRol(String rol);
	//List<Usuario> findByRoles(String roles);
	

	
	/** Delete **/
	@Cacheable
	Usuario deleteByMaiLogin(String maiLogin);
	
	
	/**Insert**/
	@CacheEvict(allEntries=true)
	Usuario save(Usuario usuario);
	
	
}
