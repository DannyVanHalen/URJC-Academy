package com.dad.urjcacademy.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class URJC_Academy_SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure (HttpSecurity https) throws Exception {
		
		/*Public Pages*/
		https.authorizeRequests().antMatchers("/").permitAll();
		https.authorizeRequests().antMatchers("/login").permitAll();
		https.authorizeRequests().antMatchers("/loginError").permitAll();
		https.authorizeRequests().antMatchers("/logout").permitAll();
		
		/*Private Pages*/
		https.authorizeRequests().anyRequest().authenticated();
		
		// Login form 
		https.formLogin().loginPage("/login");
		https.formLogin().usernameParameter("login");
		https.formLogin().passwordParameter("pass");
		https.formLogin().defaultSuccessUrl("/root");
		https.formLogin().defaultSuccessUrl("/profesor");
		https.formLogin().defaultSuccessUrl("/alumno");
		https.formLogin().failureUrl("/loginError");
		
		// Logout 
		https.logout().logoutUrl("/logout");
		https.logout().logoutSuccessUrl("/");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// User
		
	}
	
	
}
