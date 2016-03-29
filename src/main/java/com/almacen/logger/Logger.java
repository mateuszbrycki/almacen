package com.almacen.logger;

import com.almacen.logger.service.LoggerService;
import com.almacen.logger.status.Status;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logger extends LoggerAbstract {

    @Inject
    private UserService userService;

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

    @Override
    public void log(Status status, String message, User user) {
        LoggerMessage loggerMessage = this.factory.getMessage(status, message);
        loggerMessage.setUser(user);

        this.service.create(loggerMessage);
    }

    @Override
    public void log(Status status, String message, HttpServletRequest request, HttpServletResponse response) {
        LoggerMessage loggerMessage = this.factory.getMessage(status, message);

        try {
            User user = this.userService.findUserById(UserUtils.getUserId(request, response));
            loggerMessage.setUser(user);

        } catch(UserNotFoundException e) {}

        this.service.create(loggerMessage);
    }
}
