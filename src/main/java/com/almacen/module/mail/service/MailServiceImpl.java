package com.almacen.module.mail.service;


import com.almacen.module.configuration.repository.PropertyRepository;
import com.almacen.module.mail.Mail;
import com.almacen.module.mail.property.MailProperty;
import com.almacen.module.share.service.ShareService;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@org.springframework.stereotype.Service("mailService")
public class MailServiceImpl implements MailService {

    @Inject
    UserService userService;
    @Inject
    ShareService shareService;
    @Inject
    private Environment environment;
    @Resource
    private PropertyRepository propertyRepository;

    @Override
    public boolean send(String toEmail, String subject, String bodyEmail) {
        try {

            Properties props = MailProperty.set();
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(Mail.FROM_EMAIL, Mail.PASSWORD);
                }
            };
            Session session = Session.getInstance(props, auth);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(Mail.FROM_EMAIL);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(bodyEmail);
            Transport.send(message);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;

        }
    }

    @Override
    public Mail createBody(Integer userId, Integer folderId, HttpServletRequest request) throws UserNotFoundException {
        String userUsername = userService.findUserById(userId).getUsername();
        String subject = "Almacen send you shared folder from " + userUsername;
        String url = shareService.getShareUrl(request.getRequestURL().toString().split("/", 3));
        String folderToken = folderId.toString();
        String emailBody = "Please visit " + url + "/" + shareService.encode(folderToken) + " to look at shared files ";
        return new Mail(subject, emailBody);
    }
}
