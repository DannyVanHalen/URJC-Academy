package com.dad.urjcacademy.entity;

import javax.persistence.Entity;

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
	}
	
	
	
}
