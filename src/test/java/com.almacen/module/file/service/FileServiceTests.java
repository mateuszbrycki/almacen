package com.almacen.module.file.service;

import com.almacen.config.test.TestAppConfig;
import com.almacen.module.file.UserFile;
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
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestAppConfig.class)
@WebIntegrationTest
@Transactional
public class FileServiceTests {

    @Inject
    private UserService userService;

    @Inject
    private FileService fileService;

    @Inject
    private UserRoleService userRoleService;

    private UserFile userFile;

    private User testUser;

    @Before
    public void initObjects(){
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRoleService.saveUserRole(userRole);

        testUser = new User();
        testUser.setMail("test@test.gmail.com");
        testUser.setUsername("testusername");
        testUser.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword");

            this.userService.registerUser(testUser);

        userFile = new UserFile();
        userFile.setUser(testUser);
        userFile.setName("testName");
        userFile.setExtension("txt");
        userFile.setSize(100L);

            this.fileService.saveFile(userFile);
    }

    @Test
    public void saveFile() {
        UserFile tempFile = null;

            try {
                tempFile = fileService.findUserFileByFileId(userFile.getFileId());
            } catch(FileNotFoundException e) {
                fail("File not found!");
            }

        assertNotNull(tempFile);
        assertEquals(userFile.getName(), tempFile.getName());
        assertEquals(userFile.getExtension(), tempFile.getExtension());
        assertEquals(userFile.getUser().getId(), tempFile.getUser().getId());
        assertEquals(userFile.getSize(), tempFile.getSize());
    }

    @Test
    public void findFileByName() {
        UserFile testFile = fileService.findUserFileByName(userFile.getName(), testUser.getId());

            assertNotNull(testFile);
            assertEquals(userFile.getName(), testFile.getName());
            assertEquals(userFile.getExtension(), testFile.getExtension());
            assertEquals(userFile.getUser().getId(), testFile.getUser().getId());
            assertEquals(userFile.getSize(), testFile.getSize());
    }

    @Test
    public void findFileById() throws FileNotFoundException {
        UserFile testFile = fileService.findUserFileByFileId(userFile.getFileId());

            assertNotNull(testFile);
            assertEquals(userFile.getName(), testFile.getName());
            assertEquals(userFile.getExtension(), testFile.getExtension());
            assertEquals(userFile.getUser().getId(), testFile.getUser().getId());
            assertEquals(userFile.getSize(), testFile.getSize());
    }

    @Test(expected = FileNotFoundException.class)
    public void deleteFileById() throws FileNotFoundException {
        int tempId = userFile.getFileId();

        fileService.deleteFileByFileIdAndUserId(tempId, testUser.getId());

        UserFile nullFile = fileService.findUserFileByFileId(tempId);
    }
}
