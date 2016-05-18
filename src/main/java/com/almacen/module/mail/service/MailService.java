package com.almacen.module.mail.service;

import com.almacen.module.configuration.Property;

public interface MailService {

        void send(String toEmail, String subject, String bodyEmail);
        Property emailPassword();
        Property emailLogin();

}
