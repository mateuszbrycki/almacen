package com.almacen.logger;

import com.almacen.logger.service.LoggerService;
import com.almacen.logger.status.Status;
import com.almacen.module.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class LoggerAbstract {

    protected LoggerService service;
    protected LoggerMessageFactoryAbstract factory;

    public LoggerAbstract(LoggerService service, LoggerMessageFactoryAbstract factory) {
        this.service = service;
        this.factory = factory;
    }

    public abstract void log(Status status, String message);
    public abstract void log(Status status, String message, User user);
    public abstract void log(Status status, String message, HttpServletRequest request);
    public abstract void log(Status status, String message, HttpServletRequest request, HttpServletResponse response);
}
