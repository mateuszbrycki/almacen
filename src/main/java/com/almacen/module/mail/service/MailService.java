package com.almacen.module.mail.service;

import com.almacen.module.configuration.Property;

public interface MailService {

        boolean send(String toEmail, String subject, String bodyEmail);
        Property emailPassword();
        Property emailLogin();

}
