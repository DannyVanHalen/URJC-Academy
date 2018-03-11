package com.dad.urjcacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.urjcacademy.entity.Tutoria;

public interface TutoriaRepository extends JpaRepository<Tutoria,Long> {

	List<Tutoria> findByDayOfMonthAndMonthAndYear(int dayOfMonth, int month, int year);
	List<Tutoria> findByMonthAndYear(int month, int year);	
	
}
