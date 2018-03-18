package com.dad.urjcacademy.services;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Admin;
//import com.dad.urjcacademy.entity.Admin;
import com.dad.urjcacademy.repository.UsuarioRepository;

@Component
public class URJCAcademyDBLoader {

	@Autowired
	private UsuarioRepository usuarios;
	
	
	@PostConstruct
	public void initdDB() {
		/**
		if(usuarios.findByLogin("root") == null) {
			usuarios.save(new Admin("root","urjc.academy.root@gmail.com","sudosu12345@","administrador"));
		}**/
		
		if(usuarios.findByLogin("root") == null)
			usuarios.save(new Admin("root","urjc.academy.root@gmail.com","sudosu12345@","administrador","ROLE_ADMIN"));
		
	}
	
}
