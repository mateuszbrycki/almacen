package com.almacen.logger;

import com.almacen.logger.service.LoggerService;
import com.almacen.logger.status.Status;

import javax.servlet.http.HttpServletRequest;

public class Logger extends LoggerAbstract {

    public Logger(LoggerService service, LoggerMessageFactoryAbstract factory) {
        super(service, factory);
    }

    @Override
    public void log(Status status, String message) {
        LoggerMessage loggerMessage = this.factory.getMessage(status, message);
        this.service.create(loggerMessage);
    }

    @Override
    public void log(Status status, String message, HttpServletRequest request) {
        LoggerMessage loggerMessage = this.factory.getMessage(status, message, request);
        this.service.create(loggerMessage);
    }
}
