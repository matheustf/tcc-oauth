package com.puc.tcc.oauth.config.email;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class EmailController {
	
	@Autowired
	private EmailSenderComponent emailSenderComponent;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> sayHello() throws MessagingException, MailException, IOException {

         emailSenderComponent.emailBoasVindas("Matheus", "teles.matheus@hotmail.com");
    	
        return new ResponseEntity<>("Secured hello!", HttpStatus.OK);
    }
}
