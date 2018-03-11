package com.dad.urjcacademy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dad.urjcacademy.entity.Tutoria;
import com.dad.urjcacademy.repository.TutoriaRepository;

@Component
public class TutoriaService {

	@Autowired
	private TutoriaRepository repository;
	
	/*Select*/

	public Tutoria findById(long id) {
		return repository.findOne(id);
	}
	
	public List<Tutoria> findByDay(int day,int month, int year) {
		return repository.findByDayOfMonthAndMonthAndYear(day, month, year);
	}
	
	public List<Tutoria> findByMonth(int month, int year) {
		return repository.findByMonthAndYear(month, year);
	}
	
	public List<Tutoria> findAll() {
		return repository.findAll();
	}
	
	/*Insert*/
	
	public Tutoria save(Tutoria tutoria) {
		return repository.save(tutoria);
	}
	
	/*Delete*/
	public void delete(long id) {
		repository.delete(id);
	}
	
	public void delete(Tutoria tutoria) {
		repository.delete(tutoria);
	}
	
	
	

	
}
