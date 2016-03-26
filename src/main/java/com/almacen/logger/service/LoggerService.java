package com.almacen.logger.service;

import com.almacen.logger.*;
import com.almacen.logger.exception.LoggerMessageNotFound;

import java.util.List;

public interface LoggerService {
    LoggerMessage create(LoggerMessage LoggerMessage);
    LoggerMessage delete(int id) throws LoggerMessageNotFound;
    List findAll();
    LoggerMessage update(LoggerMessage LoggerMessage) throws LoggerMessageNotFound;
    LoggerMessage findById(int id);
}
