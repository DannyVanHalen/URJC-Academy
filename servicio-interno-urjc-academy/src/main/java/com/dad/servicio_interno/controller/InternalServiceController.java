package com.dad.servicio_interno.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dad.servicio_interno.mail.Mail;
import com.dad.servicio_interno.protocol.MySimpleMailTransferProtocol;


@RestController
public class InternalServiceController {

	@Autowired
	private MySimpleMailTransferProtocol smtp;
	
	/*
	public ResponseEntity<Mail> sendEmail(Model model, @RequestBody Mail email) {
		return new ResponseEntity<>(email,HttpStatus.OK);
	}*/
	
	@RequestMapping(value="/send", method=RequestMethod.GET)
	public ResponseEntity<Mail> sendEmailTest() {
		
		Mail email1 = new Mail("urjc.academy.root@gmail.com","danny.van.halen.87@gmail.com","email test URJC Academy", "Hello World :-D!!!");
		//String [] parameters = {email1.getFrom(),email1.getTo(),email1.getSubject(),email1.getBody()};
		if(smtp.send(email1.getParametersArray())) {
			return new ResponseEntity<Mail>(email1,HttpStatus.OK);
		}
		
		return new ResponseEntity<Mail>(email1,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
}
