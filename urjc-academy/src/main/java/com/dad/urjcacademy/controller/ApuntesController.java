package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.repository.ApuntesRepository;

@Controller
public class ApuntesController {

	@Autowired
	private ApuntesRepository repository;
	
}
