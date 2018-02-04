package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.TitulacionRepository;

@Controller
public class TitulacionController {

	@Autowired
	private TitulacionRepository repository;
	
}
