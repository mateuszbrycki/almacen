package com.almacen.config.logger;

import com.almacen.logger.Logger;
import com.almacen.logger.LoggerAbstract;
import com.almacen.logger.LoggerMessageFactoryAbstract;
import com.almacen.logger.factory.LoggerMessageFactory;
import com.almacen.logger.service.LoggerService;
import com.almacen.logger.service.LoggerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
@ComponentScan({ "org.pac4j.springframework.web", "com.almacen"})
public class LoggerConfig {

    @Bean
    public LoggerService loggerService() {
        return new LoggerServiceImpl();
    }

    @Bean
    public LoggerMessageFactoryAbstract loggerMessageFactory() {
        return new LoggerMessageFactory();
    }

    @Bean
    @Inject
    public LoggerAbstract logger(LoggerService loggerService, LoggerMessageFactoryAbstract loggerMessageFactoryAbstract) {
        return new Logger(loggerService, loggerMessageFactoryAbstract);
    }
}
