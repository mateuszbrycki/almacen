package com.almacen.module.user.service;

import com.almacen.config.AppConfig;
import com.almacen.module.user.User;
import com.almacen.module.userrole.service.UserRoleService;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=AppConfig.class)
@WebIntegrationTest
public class UserServiceTests {

    @Inject
    UserService userService;

    @Inject
    UserRoleService userRoleService;

    @Inject
    @Qualifier("sessionFactoryMySQL")
    SessionFactory sessionFactory;

    User testUser;

    private static final Logger logger = Logger.getLogger(UserServiceTests.class);

    @Before
    public void initObjects() {
        testUser = new User();
        testUser.setMail("test5@test.gmail.com");
        testUser.setUsername("testusername5");
        testUser.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword5");
        userService.registerUser(testUser);
    }

    @Test
    public void saveUser() {
        User newUser = userService.findUserById(testUser.getId());

        assertNotNull(newUser);
        assertEquals("test5@test.gmail.com", newUser.getMail());
        assertEquals("testusername5", newUser.getUsername());
        assertEquals(User.DEFAULT_ROLE, newUser.getRole().getRole());
        assertEquals("testpassword5", newUser.getPassword());
    }

    @Test
    public void updateUser() {

        testUser.setPassword("newpassword");

        userService.updateUser(testUser);

        User newUser = userService.findUserById(testUser.getId());

        assertNotNull(newUser);
        assertEquals("newpassword", newUser.getPassword());

    }

    @Test
    public void deleteUserById() {
        int temp = testUser.getId();

        userService.deleteUserById(testUser.getId());

        User deletedUser = userService.findUserById(temp);

        assertNull(deletedUser);
    }

    @Test
    public void checkIfUserWithMailExists() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        assertTrue(userService.checkIfUserWithMailExists("test2@test.gmail.com"));
        assertFalse(userService.checkIfUserWithMailExists("exampleusername"));

        userService.deleteUserById(testUser2.getId());
    }

    @Test
    public void checkIfUserWithUsernameExists() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        assertTrue(userService.checkIfUserWithUsernameExists("testusername2"));
        assertFalse(userService.checkIfUserWithUsernameExists("exampleusername"));

        userService.deleteUserById(testUser2.getId());
    }

    @Test
    public void getUserIdByUsername() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        assertEquals((Integer) testUser2.getId(), (Integer) userService.getUserIdByUsername("testusername2"));

        userService.deleteUserById(testUser2.getId());
    }

    @Test
    public void findUserById() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        assertEquals("testusername2", userService.findUserById(testUser2.getId()).getUsername());

        userService.deleteUserById(testUser2.getId());
    }

    @Test
    public void findUsersByUsername() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        List<User> users = userService.findUsersByUsername("testusername");

        assertEquals(2, users.size());

        userService.deleteUserById(testUser2.getId());

        users = userService.findUsersByUsername("testusername");

        assertEquals(1, users.size());
    }

    @Test
    public void findUsersByUsernameWithoutTypingUser() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByName(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        List<User> users = userService.findUsersByUsername("testusername", testUser.getId());

        assertEquals(1, users.size());

        userService.deleteUserById(testUser2.getId());

        users = userService.findUsersByUsername("testusername", testUser.getId());

        assertEquals(0, users.size());
    }

    @After
    public void clearDatabase() {
        userService.deleteUserById(testUser.getId());

    }

}
