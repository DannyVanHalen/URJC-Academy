package com.dad.urjcacademy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("")
public class URJCAcademyController {
	
	
	@RequestMapping(value= {"/"}, method=RequestMethod.GET)
	public String urjc_academy(Model model) {
		return "urjc-academy";
	}
	
	
	
}
