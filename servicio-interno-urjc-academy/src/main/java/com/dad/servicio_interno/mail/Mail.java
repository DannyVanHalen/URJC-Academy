package com.dad.servicio_interno.mail;

public class Mail {

	
	private String loginUsuario;
	private String passUsuario;
	private String maiLoginUsuario;
	private String asuntoMail;
	
	public Mail() {}
	
	public Mail(String loginUsuario, String passUsuario, 
			String maiLoginUsuario, String asuntoMail) {
		this.loginUsuario = loginUsuario;
		this.passUsuario = passUsuario;
		this.maiLoginUsuario = maiLoginUsuario;
		this.asuntoMail = asuntoMail;
	}
	
	public String getLoginUsuario() {
		return loginUsuario;
	}
	
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	
	public String getPassUsuario() {
		return passUsuario;
	}
	
	public void setPassUsuario(String passUsuario) {
		this.passUsuario = passUsuario;
	}
	
	public String getMaiLoginUsuario() {
		return maiLoginUsuario;
	}
	
	public void setMaiLoginUsuario(String maiLoginUsuario) {
		this.maiLoginUsuario = maiLoginUsuario;
	}
	
	public String getAsuntoMail() {
		return asuntoMail;
	}
	
	public void setAsuntoMail(String asuntoMail) {
		this.asuntoMail = asuntoMail;
	}
	
	@Override
	public String toString() {
		return "Correo[desinatario = " + loginUsuario + "<" + maiLoginUsuario + ">|n" + " asunto = " + asuntoMail;
	}
	
	
	
	
}
