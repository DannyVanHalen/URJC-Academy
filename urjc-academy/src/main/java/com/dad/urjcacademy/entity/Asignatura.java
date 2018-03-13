package com.dad.urjcacademy.entity;

import java.util.List;
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
	
	@ManyToOne
	private Titulacion titulacion;
	
	@ManyToOne()
	private Profesor profesor;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Apuntes> apuntes;
	
	/** Constructores **/
	
	public Asignatura() {
		
	}
	
	public Asignatura(String nombre, Titulacion titulacion, Profesor profesor,
			 List<Alumno> alumnos, List<Apuntes> apuntes) {
		
		this.nombre = nombre;
		this.titulacion = titulacion;
		this.profesor = profesor;
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
	
	public Titulacion getTitulacion() {
		return titulacion;
	}
	
	public Profesor getProfesor() {
		return profesor;
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
	
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}
	
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
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
