package com.dad.urjcacademy.security;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.services.UsuarioService;


@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioService userService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		// Cargamos el usuario de la Base de Datos
		Usuario user = userService.findByLogin(auth.getName());
		System.out.println(auth.getDetails().toString());
		
		if(user == null) {
			throw new BadCredentialsException("User not found");
		}
		
		String password = (String) auth.getCredentials();
		if(!new BCryptPasswordEncoder().matches(password, user.getPass())) {
			throw new BadCredentialsException("Wrong password");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		for(String role : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
	
		return new UsernamePasswordAuthenticationToken(user.getLogin(),password,roles);
	
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
