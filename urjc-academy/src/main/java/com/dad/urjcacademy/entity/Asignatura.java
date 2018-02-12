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
	
	@ManyToMany(mappedBy="asignaturas")
	private List<Alumno> alumnos;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Apuntes> apuntes;
	
	/** Constructores **/
	
	public Asignatura() {
		
	}
	
	public Asignatura(String nombre, 
			 List<Alumno> alumnos, List<Apuntes> apuntes) {
		
		this.nombre = nombre;
		
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
	
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void setApuntes(List<Apuntes> apuntes) {
		this.apuntes = apuntes;
	}
	
	/** Metodos Funcionales **/
	
	// Privados || Protegidos
	
	// Públicos 
	
	public boolean apuntarAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}
	
	public boolean desapuntarAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}
	
	public boolean apuntarAlumnos(List<Alumno> alumnos) {
		return alumnos.addAll(alumnos);
	}
	
	public boolean desapuntarAlumnos(List<Alumno> alumnos) {
		return alumnos.removeAll(alumnos);
	}
	
	
	
}
