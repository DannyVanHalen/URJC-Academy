package com.dad.urjcacademy.entity;

<<<<<<< HEAD
import javax.persistence.Entity;

import org.springframework.web.context.annotation.SessionScope;

@Entity
public class Admin extends Usuario {

	/** Constructor **/
	
	public Admin() {
		super();
	}
	
	public Admin(String login, String maiLogin, String pass, String rol) {
		super(login,maiLogin,pass,rol);
	}
	
	
	/** Metodo de acceso **/
	
	// GET 
	
	@Override
	public long getId() {
		return super.getId();
	}
	
	@Override
	public String getLogin() {
		return super.getLogin();
	}
	
	@Override
	public String getMaiLogin() {
		return super.getMaiLogin();
	}
	
	
	@Override
	public String getPass() {
		return super.getPass();
	}
	
	@Override
	public String getRol() {
		return super.getRol();
	}
	
	
	// SET 
	
	@Override
	public void setLogin(String login) {
		super.setLogin(login);
	}
	
	@Override
	public void setMaiLogin(String maiLogin) {
		super.setMaiLogin(maiLogin);
	}
	
	@Override
	public void setPass(String pass) {
		super.setPass(pass);
	}
	
	@Override
	public void setRol(String rol) {
		super.setRol(rol);
=======
import java.util.List;

import javax.persistence.Entity;


@Entity
public class Admin extends Usuario {

	/** Constructor **/
	
	public Admin() {
		super();
	}
	
	public Admin(String login, String maiLogin, String pass, String rol, String... roles) {
		super(login,maiLogin,pass,rol,roles);
	}
	
	
	/** Metodo de acceso **/
	
	// GET 
	
	@Override
	public long getId() {
		return super.getId();
	}
	
	@Override
	public String getLogin() {
		return super.getLogin();
	}
	
	@Override
	public String getMaiLogin() {
		return super.getMaiLogin();
	}
	
	@Override
	public String getPass() {
		return super.getPass();
	}
	
	@Override
	public String getRol() {
		return super.getRol();
	}
	
	@Override
	public List<String> getRoles() {
		return super.getRoles();
	}
	
	// SET 
	
	@Override
	public void setLogin(String login) {
		super.setLogin(login);
	}
	
	@Override
	public void setMaiLogin(String maiLogin) {
		super.setMaiLogin(maiLogin);
	}
	
	@Override
	public void setPass(String pass) {
		super.setPass(pass);
	}
	
	@Override
	public void setRol(String rol) {
		super.setRol(rol);
	}
	
	@Override
	public void setRoles(List<String> roles) {
		super.setRoles(roles);
>>>>>>> refs/remotes/origin/develop
	}
	
	
	
}
