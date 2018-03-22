package com.dad.urjcacademy.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tutoria {

	/** Atributos **/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int dayOfMonth;
	private int month;
	private int year;
	private int hour;
	private int min;
	
	private boolean validate;
	private String fecha;
	
	
	/** Constructores **/
	
	public Tutoria() {
		
	}
	
	public Tutoria(int dayOfMonth, int month, int year, int hour, int min,boolean validate,
			Asignatura asignaturaTutoria) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.of(year,month,dayOfMonth,hour,min);
		this.fecha = dateTime.format(formatter);
		this.dayOfMonth = dateTime.getDayOfMonth();
		this.month = dateTime.getMonthValue();
		this.year = dateTime.getYear();
		this.hour = dateTime.getHour();
		this.min = dateTime.getMinute();
		this.validate = validate;

		
	}
	
	
	/** Métodos de Acceso **/
	
	// GET
	
	public long getId() {
		return id;
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
	
	public boolean getValidate() {
		return validate;
	}
	
	public String getFecha() {
		return fecha;
	}

	
	// SET
	
	
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
	
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	/** Metodos Funcionales de la Entidad **/
	
	/**
	public void cambiarFechaTutoria(int dayOfMonth, int month, int year, int hour, int min, boolean validate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.of(year,month,dayOfMonth,hour,min);
		this.fecha = dateTime.format(formatter);
		this.dayOfMonth = dayOfMonth;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.min = min;
		this.validate = validate;
	}**/
	
	
	
	
}
