package com.almacen.logger;

import com.almacen.logger.status.Status;

import javax.servlet.http.HttpServletRequest;

public abstract class LoggerMessageFactoryAbstract {

    public abstract LoggerMessage getMessage(Status status, String message);
    public abstract LoggerMessage getMessage(Status status, String message, HttpServletRequest request);
}
