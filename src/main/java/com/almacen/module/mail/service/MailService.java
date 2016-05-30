package com.almacen.module.mail.service;

import com.almacen.module.mail.Mail;
import com.almacen.module.user.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface MailService {

        boolean send(String toEmail, String subject, String bodyEmail);
        Mail createBody(Integer userId, Integer folderId, HttpServletRequest request) throws UserNotFoundException;

}
