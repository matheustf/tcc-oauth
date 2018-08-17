package com.puc.tcc.oauth.security.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.puc.tcc.oauth.config.mail.EmailService;
import com.puc.tcc.oauth.config.mail.Mail;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
	
	@Autowired
	private EmailService emailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> sayHello() throws MessagingException {

         Mail mail = new Mail();
         mail.setFrom("mailtestmatheus@gmail.com");
         mail.setTo("mailtestmatheus@gmail.com");
         mail.setSubject("Sending Simple Email with JavaMailSender Example");
         mail.setContent("This tutorial demonstrates how to send a simple email using Spring Framework.");

         emailService.sendSimpleMessage(mail);
    	
    	
        return new ResponseEntity<>("Secured hello!", HttpStatus.OK);
    }
}
