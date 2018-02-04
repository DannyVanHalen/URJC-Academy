package com.dad.urjcacademy.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tutoria {

	/** Atributos **/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private LocalDateTime fecha;
	private int dayOfMonth;
	private int month;
	private int year;
	private int hour;
	private int min;
	
	@ManyToOne
	private Asignatura asignatura;
	
	@ManyToOne
	private Profesor profesor;
	
	@ManyToOne
	private Alumno alumno;
	
	/** Constructores **/
	
	public Tutoria() {
		
	}
	
	public Tutoria(LocalDateTime fecha, 
			Asignatura asignatura, Profesor profesor, Alumno alumno) {
		
		this.fecha = fecha;
		this.dayOfMonth = fecha.getDayOfMonth();
		this.month = fecha.getMonthValue();
		this.year = fecha.getYear();
		this.hour = fecha.getHour();
		this.min = fecha.getMinute();
		
		this.asignatura = asignatura;
		this.profesor = profesor;
		this.alumno = alumno;
		
	}
	
	/** Métodos de Acceso **/
	
	// GET
	
	public long getId() {
		return id;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getHour() {
		return hour;
	}
	
	public int getMin() {
		return min;
	}
	
	public Asignatura getAsignatura() {
		return asignatura;
	}
	
	public Profesor getProfesor() {
		return profesor;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}
	
	
	// SET
	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	
	public void setMonth(int  month) {
		this.month = month;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	/** Metodos Funcionales de la Entidad **/
	
	
	
	
}
