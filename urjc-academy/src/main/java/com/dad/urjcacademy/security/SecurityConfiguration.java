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
	protected void configure (HttpSecurity http) throws Exception {
		
		/*Public Pages*/
		http.authorizeRequests().antMatchers("/","/login","/loginError").permitAll();

		/*Private Pages*/
		http.authorizeRequests().anyRequest().authenticated();
		
		// Login form 
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("login");
		http.formLogin().passwordParameter("pass");
		http.formLogin().defaultSuccessUrl("/logged");
		http.formLogin().failureUrl("/loginError");
		
		// Logout 
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
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
