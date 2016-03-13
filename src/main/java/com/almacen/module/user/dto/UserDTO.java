package com.almacen.module.user.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    @Length(min = 3)
    private String username;

    @NotNull
    @Email
    private String mail;

    @NotNull
    @Length(min = 3)
    private String password;

    @NotNull
    @Length(min = 3)
    private String passwordRepeat;

    public UserDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String repeatPassword) {
        this.passwordRepeat = repeatPassword;
    }

    @Override
    public String toString() {
        return "Username: " + this.username + ", mail: " + this.mail + ", password: " + this.password + ", repeat: " + this.passwordRepeat;
    }
}
