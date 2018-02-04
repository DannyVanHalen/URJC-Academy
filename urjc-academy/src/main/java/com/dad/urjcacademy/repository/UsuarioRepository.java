package com.dad.urjcacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/** Select **/
	Usuario findByMaiLogin(String maiLogin);
	List<Usuario> findByRol(String rol);
	
	/** Delete **/
	Usuario deleteById(long id);
	Usuario deleteByMaiLogin(String maiLogin);
	
}
