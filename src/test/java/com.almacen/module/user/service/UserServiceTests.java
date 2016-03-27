package com.almacen.module.user.service;

import com.almacen.config.test.TestAppConfig;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.userrole.UserRole;
import com.almacen.module.userrole.service.UserRoleService;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
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
public class UserServiceTests {

    @Inject
    UserService userService;

    @Inject
    UserRoleService userRoleService;

    User testUser;

    private static final Logger logger = Logger.getLogger(UserServiceTests.class);

    @Before
    public void initObjects() {

        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRoleService.saveUserRole(userRole);

        UserRole userRole1 = new UserRole();
        userRole1.setRole("ROLE_ADMIN");
        userRoleService.saveUserRole(userRole1);

        testUser = new User();
        testUser.setMail("test5@test.gmail.com");
        testUser.setUsername("testusername5");
        testUser.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword5");
        userService.registerUser(testUser);
    }

    @Test
    public void saveUser() {
        User newUser = null;
        try {
            newUser = userService.findUserById(testUser.getId());
        } catch(UserNotFoundException e) {
            fail("User not found.");
        }

        assertNotNull(newUser);
        assertEquals("test5@test.gmail.com", newUser.getMail());
        assertEquals("testusername5", newUser.getUsername());
        assertEquals(User.DEFAULT_ROLE, newUser.getRole().getRole());
        assertEquals("testpassword5", newUser.getPassword());
    }

    @Test
    public void updateUser()  {

        testUser.setPassword("newpassword");

        userService.updateUser(testUser);

        User newUser = null;
        try {
            newUser = userService.findUserById(testUser.getId());
        } catch(UserNotFoundException e) {
            fail("User not found.");
        }

        assertNotNull(newUser);
        assertEquals("newpassword", newUser.getPassword());

    }

    @Test(expected=UserNotFoundException.class)
    public void deleteUserById() throws UserNotFoundException {
        int temp = testUser.getId();

        userService.deleteUserById(testUser.getId());

        User deletedUser = userService.findUserById(temp);
    }

    @Test
    public void checkIfUserWithMailExists() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
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
        testUser2.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        assertTrue(userService.checkIfUserWithUsernameExists("testusername2"));
        assertFalse(userService.checkIfUserWithUsernameExists("exampleusername"));

        userService.deleteUserById(testUser2.getId());
    }

    @Test
    public void getUserIdByUsername()  {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        try {
            assertEquals(testUser2.getId(),userService.getUserIdByUsername("testusername2"));
        } catch(UserNotFoundException e) {
            fail("User not found.");
        }
        userService.deleteUserById(testUser2.getId());
    }

    @Test
    public void findUserById()  {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        try {
            assertEquals("testusername2", userService.findUserById(testUser2.getId()).getUsername());
        } catch(UserNotFoundException e) {
            fail("User not found.");
        }
        userService.deleteUserById(testUser2.getId());
    }

    @Test
    public void findUsersByUsername() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        List<User> users = null;
        try {
           users = userService.findUsersByUsername("testusername");
        } catch(UserNotFoundException e) {
            fail("User not found.");
        }

        assertEquals(2, users.size());

        userService.deleteUserById(testUser2.getId());

        try {
            users = userService.findUsersByUsername("testusername");
        } catch(UserNotFoundException e) {
            fail("User not found.");
        }

        assertEquals(1, users.size());
    }

    @Test
    public void findUsersByUsernameWithoutTypingUser() {
        User testUser2 = new User();
        testUser2.setMail("test2@test.gmail.com");
        testUser2.setUsername("testusername2");
        testUser2.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser2.setPassword("testpassword2");
        userService.registerUser(testUser2);

        List<User> users = null;
        try {
            users = userService.findUsersByUsername("testusername", testUser.getId());
        } catch(UserNotFoundException e) {
            fail("User not found.");
        }
        assertEquals(1, users.size());

        userService.deleteUserById(testUser2.getId());

        users = null;
        try {
            users = userService.findUsersByUsername("testusername", testUser.getId());
        } catch(UserNotFoundException e) {
            assertNull(users);
        }
    }
}
