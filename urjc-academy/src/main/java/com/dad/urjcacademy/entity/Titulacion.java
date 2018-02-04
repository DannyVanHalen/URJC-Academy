package com.dad.urjcacademy.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Titulacion {

	/** Atributos **/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String rama;
	
	@OneToMany(mappedBy="titulacion")
	private List<Asignatura> asignaturas;
	
	
	
	/** Constructores **/
	
	public Titulacion() {
		
	}
	
	public Titulacion(String nombre, String rama, List<Asignatura> asignaturas) {
		
		this.nombre = nombre;
		this.rama = rama;
		
		this.asignaturas = asignaturas;
		
	}
	
	
	/** Metodos de Acceso **/
	
	// GET
	
	public long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getRama() {
		return rama;
	}
	
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	
	// SET
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setRama(String rama) {
		this.rama = rama;
	}
	
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
}
