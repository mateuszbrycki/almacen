package com.almacen.module.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SmtpMailSender {

	public void send()
	{

		try{
			final String fromEmail = "almacen.company@gmail.com";
			final String password = "cde34rfv";
			final String toEmail = "tomhanusiak@gmail.com"; // can be any email id

			System.out.println("TLSEmail Start");
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
			props.put("mail.smtp.port", "587"); //TLS Port
			props.put("mail.smtp.auth", "true"); //enable authentication
			props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

			//create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session session = Session.getInstance(props, auth);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			System.out.println("Mail Check 2");

			message.setSubject("Oil Error Report");
			message.setText("dasdsadsa");


			System.out.println("Mail Check 3");

			Transport.send(message);
			System.out.println("Mail Sent");
		}catch(Exception ex){
			System.out.println("Mail fail");
			System.out.println(ex);
		}
	}
}


