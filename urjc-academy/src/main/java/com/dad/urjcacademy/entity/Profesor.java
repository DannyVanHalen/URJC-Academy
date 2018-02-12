package com.dad.urjcacademy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
//import org.springframework.web.context.annotation.SessionScope;

@Entity
public class Profesor extends Usuario {

	/** Atributos **/
	
	private String nombre;
	private String apellido;
	private String tlf;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Tutoria> tutorias;
	
	@OneToMany
	private List<Asignatura> asignaturas;
	
	
	/** Constructores **/
	
	public Profesor() {
		super();
	}
	
	public Profesor(String login, String maiLogin, String pass,  String rol,
			String nombre, String apellido, String tlf
			,List<Tutoria> tutorias, List<Asignatura> asignaturas) {
		
		super(login,maiLogin,pass,rol);
		this.nombre = nombre;
		this.apellido = apellido;
		this.tlf = tlf;
		
		this.tutorias = tutorias;
		this.asignaturas = asignaturas;
		
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
	public String getRol() {
		return super.getRol();
	}
	
	@Override
	public String getPass() {
		return super.getPass();
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
	
	public List<Tutoria> getTutorias() {
		return tutorias;
	}
	
	public List<Asignatura> getAsignaturas() {
		return asignaturas;
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
	
	public void setTutorias(List<Tutoria> tutorias) {
		this.tutorias = tutorias;
	}
	
	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	/** Métodos Funcionales de la Entidad **/
	// PÚBLICOS
	
	public boolean asignarTutoria(Tutoria tutoria) {
		return tutorias.add(tutoria);
	}
	
	public boolean eliminarTutoria(Tutoria tutoria) {
		return tutorias.remove(tutoria);
	}
	
	
	
	// PROTEGÍDOS || PRIVADOS
	
	
	
	
	
	
	
	
}
