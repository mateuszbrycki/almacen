package com.almacen.logger;

import com.almacen.logger.service.LoggerService;
import com.almacen.logger.status.Status;

import javax.servlet.http.HttpServletRequest;

public abstract class LoggerAbstract {

    protected LoggerService service;
    protected LoggerMessageFactoryAbstract factory;

    public LoggerAbstract(LoggerService service, LoggerMessageFactoryAbstract factory) {
        this.service = service;
        this.factory = factory;
    }

    public abstract void log(Status status, String message);
    public abstract void log(Status status, String message, HttpServletRequest request);

}
