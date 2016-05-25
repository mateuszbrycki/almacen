package com.almacen.module.mail.service;


import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.repository.PropertyRepository;
import com.almacen.module.mail.property.MailProperty;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@org.springframework.stereotype.Service("mailService")
public class MailServiceImpl implements MailService {

    @Inject
    private Environment environment;

    @Resource
    private PropertyRepository propertyRepository;

    @Override
    public boolean send(String toEmail, String subject, String bodyEmail) {
        try{
            final String fromEmail = "almacen.company@gmail.com";
            final String password = "cde34rfv";
            Properties props = MailProperty.set();
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(emailLogin().getPropertyValue(), emailPassword().getPropertyValue());
                      return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(fromEmail);
//            message.setFrom(new InternetAddress(emailLogin().getPropertyValue()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(bodyEmail);
            Transport.send(message);
            return true;
        }catch(Exception ex){
            System.out.println(ex);
            return false;

        }
    }

    @Override
    public Property emailPassword() {
       return propertyRepository.findOneByPropertyName(environment.getProperty("property.mail.password"));
    }

    @Override
    public Property emailLogin() {
        return propertyRepository.findOneByPropertyName(environment.getProperty("property.mail.login"));
    }
}
