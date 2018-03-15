package com.dad.urjcacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registro")
public class RegistroController {

	@RequestMapping(value="", method=RequestMethod.GET)
	public String registro(Model model) {
		return "registro";
	}
	
}
