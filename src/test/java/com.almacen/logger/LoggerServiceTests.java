package com.almacen.logger;

import static org.junit.Assert.*;
import com.almacen.config.test.TestAppConfig;
import com.almacen.logger.exception.LoggerMessageNotFound;
import com.almacen.logger.service.LoggerService;
import com.almacen.logger.status.Status;
import com.almacen.module.user.User;
import com.almacen.module.user.service.UserService;
import com.almacen.module.userrole.UserRole;
import com.almacen.module.userrole.service.UserRoleService;
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
    UserService userService;

    @Inject
    UserRoleService userRoleService;

    @Inject
    private LoggerAbstract logger;

    private static String ERROR_MESSAGE = "First error";
    private static String WARNING_MESSAGE = "First warning";
    private static String INFO_MESSAGE = "First info";

    @Before
    public void initLoggerMessages() {
        this.logger.log(Status.ERROR, ERROR_MESSAGE);
        this.logger.log(Status.WARNING, WARNING_MESSAGE);
        this.logger.log(Status.INFO, INFO_MESSAGE);
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

        assertEquals(message.getMessage(), WARNING_MESSAGE);
    }

    @Test
    public void logStatus() {
        LoggerMessage message = (LoggerMessage)this.loggerService.findAll().get(0);
        assertEquals(message.getStatus(), Status.INFO);

        message = (LoggerMessage)this.loggerService.findAll().get(1);
        assertEquals(message.getStatus(), Status.WARNING);

        message = (LoggerMessage)this.loggerService.findAll().get(2);
        assertEquals(message.getStatus(), Status.ERROR);
    }

    @Test
    public void logFindUserLogs() {
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRoleService.saveUserRole(userRole);

        User testUser = new User();
        testUser.setMail("test5@test.gmail.com");
        testUser.setUsername("testusername5");
        testUser.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword5");
        userService.registerUser(testUser);

        logger.log(Status.ERROR, ERROR_MESSAGE, testUser);

        List<LoggerMessage> messages = this.loggerService.findAllByUserId(testUser.getId());
        assertEquals(messages.size(), 1);

        LoggerMessage message = messages.get(0);
        assertEquals(message.getStatus(), Status.ERROR);
        assertEquals(message.getMessage(), ERROR_MESSAGE);
        assertEquals(message.getUser().getId(), testUser.getId());
    }

}
