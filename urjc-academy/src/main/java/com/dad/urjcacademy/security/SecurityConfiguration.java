package com.dad.urjcacademy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	public UsuarioRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure (HttpSecurity https) throws Exception {
		
		/*Public Pages*/
		https.authorizeRequests().antMatchers("/","/login","/loginError");
		
		/*Private Pages*/
		https.authorizeRequests().antMatchers("/alumno").hasAnyRole("USER");
		https.authorizeRequests().antMatchers("/profesor").hasAnyRole("USER");
		https.authorizeRequests().antMatchers("/profesor/*").hasAnyRole("USER");
		https.authorizeRequests().antMatchers("/root").hasAnyRole("ADMIN");
		https.authorizeRequests().antMatchers("/root/*").hasAnyRole("ADMIN");
		
		
		// Login form 
		https.formLogin().loginPage("/login");
		https.formLogin().usernameParameter("login");
		https.formLogin().passwordParameter("pass");
		https.formLogin().defaultSuccessUrl("/logged");
		https.formLogin().failureUrl("/loginError");
		
		// Logout 
		https.logout().logoutUrl("/logout");
		https.logout().logoutSuccessUrl("/");
		
		// Deshabilito momentaneamente el CSRF
		//https.csrf().disable();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// User
		//auth.inMemoryAuthentication().withUser("login").password("pass").roles("USER");
		//auth.inMemoryAuthentication().withUser("root").password("sudosu12345@").roles("administrador");
	
		auth.authenticationProvider(authenticationProvider);
		
	}
	
	
}
