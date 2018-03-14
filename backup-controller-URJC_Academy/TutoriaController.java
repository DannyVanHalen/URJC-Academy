package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.TutoriaRepository;

@Controller
public class TutoriaController {

	@Autowired
	private TutoriaRepository repository;
	
}
