package com.dad.urjcacademy.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Alumno extends Usuario {

/** Atributos **/
	
	private String nombre;
	private String apellido;
	private String tlf;
	
	@OneToMany(cascade=CascadeType.ALL)
<<<<<<< HEAD
	private List<Tutoria> tutorias;
	
	@ManyToMany
	private List<Asignatura> asignaturas;
	
	/** Constructores **/
	
	public Alumno() {
		super();
	}
	
	public Alumno(String login, String maiLogin, String pass, String rol,
			String nombre, String apellido, String tlf,
			List<Tutoria> tutorias, List<Asignatura> asignaturas) {
		
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
=======
	private List<Tutoria> tutoriasAlumno;
	
	@ManyToMany
	private List<Asignatura> asignaturasAlumno;
	
	/** Constructores **/
	
	public Alumno() {
		super();
	}
	
	public Alumno(String login, String maiLogin, String pass, String rol,
			String nombre, String apellido, String tlf,
		    List<Asignatura> asignaturasAlumno, List<Tutoria> tutoriasAlumno, 
		    String...roles) {
		
		super(login,maiLogin,pass,rol,roles);
		this.nombre = nombre;
		this.apellido = apellido;
		this.tlf = tlf;
		
		this.tutoriasAlumno = tutoriasAlumno;
		this.asignaturasAlumno = asignaturasAlumno;
		
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
	
	/*Parte de seguridad*/
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
	public void setPass(String pass) {
		super.setPass(pass);
	}
	
	@Override
	public void setRol(String rol) {
		super.setRol(rol);
	}
	
	/*Parte de seguridad*/
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
	
	public List<Tutoria> getTutoriasAlumno() {
		return tutoriasAlumno;
	}
	
	public List<Asignatura> getAsignaturasAlumno() {
		return asignaturasAlumno;
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
	
	public void setTutoriasAlumno(List<Tutoria> tutorias) {
		this.tutoriasAlumno = tutorias;
	}
	
	public void setAsignaturasAlumno(List<Asignatura> asignaturas) {
		this.asignaturasAlumno = asignaturas;
	}
	
	/** Métodos Funcionales de la Entidad **/
	// PÚBLICOS
	
	public boolean asignarTutoria(Tutoria tutoria) {
		return tutoriasAlumno.add(tutoria);
	}
	
	public boolean eliminarTutoria(Tutoria tutoria) {
		return tutoriasAlumno.remove(tutoria);
	}
	
	public boolean eliminarTodasTutorias() {
		tutoriasAlumno.clear();
		return tutoriasAlumno.size() == 0;
	}
	
	public boolean matricularAsignatura(Asignatura asignatura) {
		return asignaturasAlumno.add(asignatura);
	}
	
	public boolean desmatricularAsignatura(Asignatura asignatura) {
		return asignaturasAlumno.remove(asignatura);
	}
	
	public boolean desmatircularTodasAsignaturas() {
		asignaturasAlumno.clear();
		return asignaturasAlumno.size() == 0;
>>>>>>> refs/remotes/origin/develop
	}
	
	// PROTEGÍDOS || PRIVADOS
	
}
