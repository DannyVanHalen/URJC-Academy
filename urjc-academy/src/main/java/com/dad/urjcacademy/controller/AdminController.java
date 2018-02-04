package com.dad.urjcacademy.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dad.urjcacademy.entity.Admin;
import com.dad.urjcacademy.repository.AdminRepository;

@Controller
public class AdminController extends UsuarioController{

	@Autowired
	private AdminRepository repository;
	
	private Admin root;
	
	@PostConstruct
	public void init() {
		
		List<Admin> roots = repository.findAll();
		
		if(roots.isEmpty()) {
			repository.save(new Admin("root","xxxxxxxx","sudosu","root"));
		} else {
			if(roots.size() == 1) {
				this.root = roots.get(0);
			}
		}
		
	}
	
	
	
	
	
}
