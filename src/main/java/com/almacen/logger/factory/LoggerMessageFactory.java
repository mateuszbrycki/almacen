package com.almacen.logger.factory;

import com.almacen.logger.LoggerMessage;
import com.almacen.logger.LoggerMessageFactoryAbstract;
import com.almacen.logger.status.Status;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LoggerMessageFactory extends LoggerMessageFactoryAbstract{
    @Override
    public LoggerMessage getMessage(Status status, String message) {

        LoggerMessage loggerMessage = new LoggerMessage();

        loggerMessage.setDate(new Date());
        loggerMessage.setMessage(message);
        loggerMessage.setStatus(status);

        return loggerMessage;
    }

    @Override
    public LoggerMessage getMessage(Status status, String message, HttpServletRequest request) {

        LoggerMessage loggerMessage = this.getMessage(status, message + request.toString());
        return loggerMessage;
    }
}
