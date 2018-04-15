package com.dad.urjcacademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dad.urjcacademy.entity.Usuario;
import com.dad.urjcacademy.internal_service.Mail;
import com.dad.urjcacademy.services.UsuarioService;

@Controller
@RequestMapping("/recuperar-pass")
public class PassRecoveryController {

	/*Instancia para el servicio Interno*/
	private static final String RESTSERVICE = "http://127.0.0.1:8070/send"; // URL del controlador Rest 
	private static final String SUBJECT = "URJC-Academy Support";
	private static final String ROOT = "urjc.academy.root@gmail.com";
	
	
	@Autowired
	private UsuarioService usuarios;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String recuperar_pass(Model model) {
		return "recuperar-pass";
	}
	
	@RequestMapping(value="/send-mail", method=RequestMethod.POST)
	public String enviar_correo(Model model, @RequestParam String maiLogin) {
		
		Usuario usuario = usuarios.findByMaiLogin(maiLogin);
		
		if(usuario != null) {
			if(!usuario.getMaiLogin().equals(ROOT)) {
			/** Serivicio Interno **/
				RestTemplate internalService = new RestTemplate();
				
				String from = usuarios.findById(1).getMaiLogin();
				String to = usuario.getMaiLogin();
				String body = "Recuperación de Contraseña";
				
				internalService.postForLocation(RESTSERVICE, new Mail(from,to,SUBJECT,body));
				
				return "redirect:/login";
			}
		
		}
		
		return "505";
	}
	
}
