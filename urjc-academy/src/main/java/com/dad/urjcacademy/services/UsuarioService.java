package com.dad.urjcacademy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Component
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	/*Select*/
	public Usuario findById(long id) {
		return repository.findOne(id);
	}
	
	public Usuario findByLogin(String login) {
		return repository.findByLogin(login);
	}
	
	public Usuario findByLoginAndPass(String login, String pass) {
		return repository.findByLoginAndPass(login, pass);
	}
	
	public Usuario findByMaiLogin(String maiLogin) {
		return repository.findByLogin(maiLogin);
	}
	
	public List<Usuario> findAll() {
		return (List<Usuario>) repository.findAll();
	}
	
	public List<Usuario> findByRol(String rol) {
		return repository.findByRol(rol);
	}
	
	/**
	public List<Usuario> findByRoles(String string) {
		return repository.findByRoles(string);
	}**/
	
	/*Insert*/
	
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}
	
	/*Delete*/
	
	public void delete(long id) {
		repository.delete(id);
	}
	
	public void delete(Usuario usuario) {
		repository.delete(usuario);
	}
	
	/*Exists*/
	
	public boolean exists(long id) {
		return repository.exists(id);
	}
	
	public boolean exists(String login, String pass) {
		return this.findByLoginAndPass(login, pass) != null;
	}
	
	
}
