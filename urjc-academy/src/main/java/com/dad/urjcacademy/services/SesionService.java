package com.dad.urjcacademy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Usuario;

@Component
public class SesionService {

	@Autowired
	private UsuarioService usuarios;
	
	public Usuario getUsuarioActual() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			return usuarios.findByLogin(authentication.getName());
		}
		
		return null;
	}
	
	
	public boolean usuarioActualHasRole(String role) {
		Usuario usuario = this.getUsuarioActual();
		return (usuario != null) && usuario.getRoles().contains(role);
	}
	
}
