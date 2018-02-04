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
	
	private String linkApuntes;
	
	@ManyToOne
	private Asignatura asignatura;
	
	/** Construcor **/
	
	public Apuntes() {
		
	}
	
	public Apuntes(String linkApuntes, Asignatura asignatura) {
		
		this.linkApuntes = linkApuntes;
		this.asignatura = asignatura;
		
	}
	
	/** Método de Acceso de la clase Apuntes **/
	
	// GET
	
	public long getId() {
		return id;
	}
	
	public String getLinkApuntes() {
		return linkApuntes;
	}
	
	public Asignatura getAsignatura() {
		return asignatura;
	}
	
	// SET
	
	/** Métodos Funcionales de la Entidad **/
	
	public void setLinkApuntes(String linkApuntes) {
		this.linkApuntes = linkApuntes;
	}
	
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
}
