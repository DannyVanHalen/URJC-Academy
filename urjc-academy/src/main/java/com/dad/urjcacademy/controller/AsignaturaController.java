package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.AsignaturaRepository;

@Controller
public class AsignaturaController {

	@Autowired
	private AsignaturaRepository repository;
	
}
