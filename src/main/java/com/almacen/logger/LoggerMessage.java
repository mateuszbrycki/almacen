package com.almacen.logger;

import com.almacen.logger.status.Status;
import com.almacen.module.user.User;
import com.almacen.module.userrole.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="logger_message")
public class LoggerMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private int id;

    @NotNull
    private String message;

    @NotNull
    @Column(name="status")
    private Status status;

    @NotNull
    @Column(name="audit_cd")
    private Date date;

    @OneToOne
    @JoinColumn(name="fk_user_id")
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
