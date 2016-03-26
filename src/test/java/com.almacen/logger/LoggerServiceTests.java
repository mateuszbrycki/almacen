package com.almacen.logger;

import static org.junit.Assert.*;
import com.almacen.config.test.TestAppConfig;
import com.almacen.logger.exception.LoggerMessageNotFound;
import com.almacen.logger.service.LoggerService;
import com.almacen.logger.status.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= TestAppConfig.class)
@WebIntegrationTest
@Transactional
public class LoggerServiceTests {

    @Inject
    private LoggerService loggerService;

    @Inject
    private LoggerAbstract logger;

    @Before
    public void initLoggerMessages() {
        this.logger.log(Status.ERROR, "First error");
        this.logger.log(Status.WARNING, "First warning");
        this.logger.log(Status.INFO, "First info");
    }

    @Test
    public void logMessage() {
        List<LoggerMessage> messages = this.loggerService.findAll();
        assertEquals(3, messages.size());
    }

    @Test
    public void deleteLogMessages() {
        LoggerMessage message = (LoggerMessage)this.loggerService.findAll().get(0);

        try {
            this.loggerService.delete(message.getId());
        } catch(LoggerMessageNotFound e) {
            fail("LoggerMessage not found.");
        }

        assertEquals(2, this.loggerService.findAll().size());
    }

    @Test
    public void findById() {
        LoggerMessage message = (LoggerMessage)this.loggerService.findAll().get(1);

        assertEquals(message.getMessage(), "First warning");
    }

    @Test
    public void logStatus() {
        LoggerMessage message = (LoggerMessage)this.loggerService.findAll().get(0);
        assertEquals(message.getStatus(), Status.ERROR);

        message = (LoggerMessage)this.loggerService.findAll().get(1);
        assertEquals(message.getStatus(), Status.WARNING);

        message = (LoggerMessage)this.loggerService.findAll().get(2);
        assertEquals(message.getStatus(), Status.INFO);
    }

}
