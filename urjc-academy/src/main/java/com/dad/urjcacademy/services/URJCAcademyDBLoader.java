package com.dad.urjcacademy.services;




import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dad.urjcacademy.entity.Admin;
import com.dad.urjcacademy.repository.UsuarioRepository;

//@Component
@Service
public class URJCAcademyDBLoader /**implements InitializingBean**/{

	@Autowired
	private UsuarioRepository usuarios;
	
	
	@PostConstruct
	public void initdDB() {
		/**
		if(usuarios.findByLogin("root") == null) {
			usuarios.save(new Admin("root","urjc.academy.root@gmail.com","sudosu12345@","administrador"));
		}**/
		
		if(usuarios.findByLogin("root") == null) {
			String [] roles = {"ROLE_USER","ROLE_ADMIN"};
			Admin admin = new Admin("root","urjc.academy.root@gmail.com","sudosu12345@","administrador",roles);
			usuarios.save(admin);
		}
			
	}

	/**
	@Override
	@Transactional()
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if(usuarios.findByLogin("root") == null) {
			String [] roles = {"ROLE_USER","ROLE_ADMIN"};
			Admin admin = new Admin("root","urjc.academy.root@gmail.com","sudosu12345@","administrador",roles);
			usuarios.save(admin);
		}
	}**/
	
}
