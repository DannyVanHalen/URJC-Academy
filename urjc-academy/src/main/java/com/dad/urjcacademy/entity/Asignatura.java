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
	
	/*
	 * Interrelación Asignatura Alumnos N:M Bidireccional
	 */
	
	@ManyToMany(mappedBy="asignaturasAlumno")
	private List<Alumno> alumnos;
	
	
	/*
	 * Interrelación Asignatura Profesores N:M Bidireccional
	 */
	
	@ManyToMany(mappedBy="asignaturasProfesor")
	private List<Profesor> profesores;
	
	
	/** Constructores **/
	
	public Asignatura() {
		
	}
	
	public Asignatura(String nombre, List<Profesor> profesores,
			 List<Alumno> alumnos) {
		
		this.nombre = nombre;
		this.profesores = profesores;
		this.alumnos = alumnos;
		
		
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
	
	public List<Profesor> getProfesores() {
		return profesores;
	}
	
	// SET
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}
	
	/** Metodos Funcionales **/
	
	// Privados || Protegidos
	
	// Públicos 
	
	// matricular alumnos
	
	public boolean matircularAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}
	
	public boolean desmatricularAlumno(Alumno alumno) {
		return alumnos.add(alumno);
	}
	
	public boolean matricularAlumnos(List<Alumno> alumnos) {
		return alumnos.addAll(alumnos);
	}
	
	public boolean desmatricularAlumnos(List<Alumno> alumnos) {
		return alumnos.removeAll(alumnos);
	}
	
	public boolean desmatricularATodos() {
		alumnos.clear();
		return alumnos.size() == 0;
	}
	
	// asociar profesores 
	
	public boolean asociaProfesor(Profesor profesor) {
		return profesores.add(profesor);
	}
	
	public boolean desasociaProfesor(Profesor profesor) {
		return profesores.add(profesor);
	}
	
	public boolean asociarProfesores(List<Profesor> profesores) {
		return profesores.addAll(profesores);
	}
	
	public boolean desasociarProfesores(List<Profesor> profesores) {
		return profesores.removeAll(profesores);
	}
	
	public boolean desasociarATodos() {
		profesores.clear();
		return profesores.size() == 0;
	}
	
	
}
