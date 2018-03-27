package com.dad.servicio_interno.protocol;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MySimpleMailTransferProtocol {

	@Autowired
	private JavaMailSender sender;
	
	/**
	 * 
	 * @param postalParametres
	 * 0 -> source address
	 * 1 -> destination address
	 * 2 -> subject 
	 * 3 -> message
	 * 
	 */
	
	public boolean send(String [] emailParameters) {
		
		MimeMessage email = sender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(email,true);
			
			helper.setFrom(emailParameters[0]);
			helper.setTo(emailParameters[1]);
			helper.setSubject(emailParameters[2]);
			helper.setText(emailParameters[3], true);
			
			sender.send(email);
			
			return true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}