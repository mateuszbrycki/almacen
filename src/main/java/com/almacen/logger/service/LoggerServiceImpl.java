package com.almacen.logger.service;

import com.almacen.logger.LoggerMessage;
import com.almacen.logger.exception.LoggerMessageNotFound;
import com.almacen.logger.repository.LoggerMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LoggerServiceImpl implements LoggerService {

    @Resource
    private LoggerMessageRepository loggerMessageRepository;

    @Override
    public LoggerMessage create(LoggerMessage loggerMessage) {
        return loggerMessageRepository.save(loggerMessage);
    }

    @Override
    @Transactional(rollbackFor=LoggerMessageNotFound.class)
    public LoggerMessage delete(int id) throws LoggerMessageNotFound {
        LoggerMessage loggerMessage = this.loggerMessageRepository.findOne(id);

        if(loggerMessage == null) {
            throw new LoggerMessageNotFound();
        }

        this.loggerMessageRepository.delete(loggerMessage);
        return loggerMessage;
    }

    @Override
    public List findAll() {
        return this.loggerMessageRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=LoggerMessageNotFound.class)
    public LoggerMessage update(LoggerMessage loggerMessage) throws LoggerMessageNotFound {
        LoggerMessage updatedMessage = this.loggerMessageRepository.findOne(loggerMessage.getId());

        if(updatedMessage == null) {
            throw new LoggerMessageNotFound();
        }

        updatedMessage.setDate(loggerMessage.getDate());
        updatedMessage.setMessage(loggerMessage.getMessage());

        return updatedMessage;
    }

    @Override
    public LoggerMessage findById(int id) {
        return this.loggerMessageRepository.findOne(id);
    }
}
