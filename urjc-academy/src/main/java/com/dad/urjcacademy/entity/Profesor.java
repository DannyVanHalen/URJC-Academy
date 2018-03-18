package com.dad.urjcacademy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
//import org.springframework.web.context.annotation.SessionScope;

@Entity
public class Profesor extends Usuario {

	/** Atributos **/
	
	private String nombre;
	private String apellido;
	private String tlf;
	
	
	@ManyToMany
	private List<Asignatura> asignaturasProfesor;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Tutoria> tutoriasProfesor;
	
	/** Constructores **/
	
	public Profesor() {
		super();
	}
	
	public Profesor(String login, String maiLogin, String pass, String rol,
			String nombre, String apellido, String tlf
			, List<Asignatura> asignaturasProfesor, List<Tutoria> tutoriasProfesor,
			String...roles) {
		
		super(login,maiLogin,pass,rol,roles);
		this.nombre = nombre;
		this.apellido = apellido;
		this.tlf = tlf;

		this.asignaturasProfesor = asignaturasProfesor;
		this.tutoriasProfesor = tutoriasProfesor;
		
	}
	
	
	/** Métodos de Acceso**/
	
	// Métodos Heredados de la clase Usuario 
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
	
	/*Parte de Seguridad*/
	@Override
	public List<String> getRoles() {
		return super.getRoles();
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
	public void setRol(String rol) {
		super.setRol(rol);
	}
	
	@Override
	public void setPass(String pass) {
		super.setPass(pass);
	}
	
	/*Parte de Seguridad*/
	@Override
	public void setRoles(List<String> roles) {
		super.setRoles(roles);
	}
	
	// Métodos nativos de la clase Profesor
	
	// GET
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getTlf() {
		return tlf;
	}
	
	public List<Asignatura> getAsignaturas() {
		return asignaturasProfesor;
	}
	
	public List<Tutoria> getTutoriasProfesor() {
		return tutoriasProfesor;
	}
	
	// SET
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturasProfesor = asignaturas;
	}
	
	public void setTutoriasProfesor(List<Tutoria> tutoriasProfesor) {
		this.tutoriasProfesor = tutoriasProfesor;
	}
	
	/** Métodos Funcionales de la Entidad **/
	// PÚBLICOS
	
	public boolean asignarAsignatura(Asignatura asignatura) {
		return asignaturasProfesor.add(asignatura);
	}
	
	public boolean quitarAsignatura(Asignatura asignatura) {
		return asignaturasProfesor.remove(asignatura);
	}
	
	public boolean agregarTutoria(Tutoria tutoria) {
		return tutoriasProfesor.add(tutoria);
	}
	
	public boolean quitarTutoria(Tutoria tutoria) {
		return tutoriasProfesor.remove(tutoria);
	}
	
	public boolean quitarTodasTutorias() {
		tutoriasProfesor.clear();
		return tutoriasProfesor.size() == 0;
	}
	
	public boolean estaAsignadaAsignatura(Asignatura asignatura) {
		return asignaturasProfesor.contains(asignatura);
	}
	
	// PROTEGÍDOS || PRIVADOS
	
	
	
	
	
	
	
	
}
