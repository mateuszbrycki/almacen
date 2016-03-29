package com.almacen.logger.service;

import com.almacen.logger.*;
import com.almacen.logger.exception.LoggerMessageNotFound;

import java.util.List;

public interface LoggerService {
    LoggerMessage create(LoggerMessage LoggerMessage);
    LoggerMessage delete(Integer id) throws LoggerMessageNotFound;
    LoggerMessage update(LoggerMessage LoggerMessage) throws LoggerMessageNotFound;

    LoggerMessage findById(Integer id);

    List findAll();
    List findAllByUserId(Integer id);
}
