package com.dad.urjcacademy.internal_service;

public class Mail {
	
	
	private String from;
	private String to;
	//private String [] to; // para mandar el mismo correo a varias direcciones
	//private String [] cc; // para mandar copia a varias direcciones
	private String subject;
	private String body;
	
	
	public Mail() {}
	
	public Mail(String from, String to, String subject, String body) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	/*Métodos de acceso*/
	//GET
	public String getFrom() { return from; }
	
	public String getTo() { return to; }
	
	public String getSubject() { return subject; }
	
	public String getBody() { return body; }
	
	//SET
	public void setFrom(String from) { this.from = from; }
	
	public void setTo(String to) { this.to = to; }
	
	public void setSubject(String subject ) { this.subject = subject; }

	public void setBody (String body) { this.body = body; }
	
	
	/*Métodos heredados de Object*/
	@Override
	public String toString() {
		return "Email[\nFrom: " + from + "\nTo: " + to + "\nSubject: " + subject + "\nMessage:\n" + body +"\n]\n";
	}
	
	
	/*Métodos nativos*/
	public String [] getParametersArray() {
		String [] parameters = {from,to,subject,body};
		return parameters;
	}
	
	
}
