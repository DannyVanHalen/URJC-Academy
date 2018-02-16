package com.dad.urjcacademy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Apuntes {

	/** Atributos **/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Asignatura asignatura;
	
	private String tema;
	private String linkApuntes;
	
	/** Construcor **/
	
	public Apuntes() {
		
	}
	
	public Apuntes(String tema, String linkApuntes) {
		
		this.tema = tema;
		this.linkApuntes = linkApuntes;
		
	}
	
	/** Método de Acceso de la clase Apuntes **/
	
	// GET
	
	public long getId() {
		return id;
	}
	
	public String getTema() {
		return tema;
	}
	
	public String getLinkApuntes() {
		return linkApuntes;
	}
	
	
	// SET
	
	/** Métodos Funcionales de la Entidad **/
	
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public void setLinkApuntes(String linkApuntes) {
		this.linkApuntes = linkApuntes;
	}
	
	
}
