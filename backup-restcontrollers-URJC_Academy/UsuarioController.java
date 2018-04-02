package com.dad.urjcacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.dad.urjcacademy.services.UsuarioService;
import com.dad.urjcacademy.entity.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarios;
	
	
	// Obetener todos los usuarios
	@GetMapping(value="/allusers")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		return new ResponseEntity<List<Usuario>>(usuarios.findAll(),HttpStatus.OK);
	}
	
}
