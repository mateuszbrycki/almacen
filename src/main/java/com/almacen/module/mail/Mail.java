package com.almacen.module.mail;

public class Mail {

    public static final String FROM_EMAIL = "almacen.company@gmail.com";
    public static final String PASSWORD = "cde34rfv";
    private String body;
    private String subject;

    public Mail(String subject, String body)
    {
        this.body = body;
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
