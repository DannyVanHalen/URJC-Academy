package com.dad.servicio_interno.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dad.servicio_interno.mail.Mail;

@RestController
public class InternalServiceController {

	@RequestMapping(value="/root/nuevo-usuario", method=RequestMethod.POST)
	public ResponseEntity<Mail> generateNewUserMail() {
		return new ResponseEntity<Mail>(new Mail(),HttpStatus.CREATED);
	}
	
}
