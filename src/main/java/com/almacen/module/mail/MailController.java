package com.almacen.module.mail;

import com.almacen.module.mail.service.MailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.mail.MessagingException;

@RestController
public class MailController {

	@Inject
	private MailService mailService;

	@RequestMapping(value = MailUrls.SENDING_MAIL, method = RequestMethod.POST)
	public void sendMail() throws MessagingException {
		mailService.send("tomhanusiak@gmail.com", "subject", "TestMessage");
		
	}
	

}
