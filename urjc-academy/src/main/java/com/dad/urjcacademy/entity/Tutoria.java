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
	


	
	/** Constructores **/
	
	public Tutoria() {
		
	}
	
	public Tutoria(LocalDateTime fecha, Asignatura asignatura) {
		
		this.fecha = fecha;
		this.dayOfMonth = fecha.getDayOfMonth();
		this.month = fecha.getMonthValue();
		this.year = fecha.getYear();
		this.hour = fecha.getHour();
		this.min = fecha.getMinute();
		
		this.asignatura = asignatura;
		
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
	
	
	/** Metodos Funcionales de la Entidad **/
	
	
	
	
}
