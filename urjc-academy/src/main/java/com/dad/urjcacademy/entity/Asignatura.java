package com.dad.urjcacademy.entity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Asignatura {

	/** Atributos **/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private AtomicInteger plazas;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Tutoria> tutorias;
	
	@ManyToOne
	private Profesor profesor;
	
	@ManyToOne
	private Titulacion titulacion;
	
	@ManyToMany
	private List<Alumno> alumnos;
	
	@OneToMany(mappedBy="asignatura")
	private List<Apuntes> apuntes;
	
	/** Constructores **/
	
	public Asignatura() {
		
	}
	
	public Asignatura(String nombre,int plazas, 
			 Profesor profesor, Titulacion titulacion, 
			 List<Alumno> alumnos, List<Apuntes> apuntes, List<Tutoria> tutorias) {
		
		this.nombre = nombre;
		this.plazas = new AtomicInteger(plazas);
		
		this.tutorias = tutorias;
		this.profesor = profesor;
		this.titulacion = titulacion;
		this.alumnos = alumnos;
		this.apuntes = apuntes;
		
	}
	
	/** Métodos de Acceso **/
	
	// GET
	
	public  long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPlazas() {
		return plazas.get();
	}
	
	public List<Tutoria> getTutorias() {
		return tutorias;
	}
	
	public Profesor getProfesor() {
		return profesor;
	}
	
	public Titulacion getTitualcion() {
		return titulacion;
	}
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	
	public List<Apuntes> getApuntes() {
		return apuntes;
	}
	
	
	// SET
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPlazas(int plazas) {
		this.plazas.set(plazas);
	}
	
	public void setTutorias(List<Tutoria> tutorias) {
		this.tutorias = tutorias;
	}
	
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void setApuntes(List<Apuntes> apuntes) {
		this.apuntes = apuntes;
	}
	
	/** Metodos Funcionales **/
	public int incrementPlaza() {
		return plazas.getAndIncrement();
	}
	
	public int decrementPlaza() {
		return plazas.getAndDecrement();
	}
	
}
