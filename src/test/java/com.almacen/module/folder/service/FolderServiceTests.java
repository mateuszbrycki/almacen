package com.almacen.module.folder.service;

import com.almacen.config.test.TestAppConfig;
import com.almacen.module.folder.Folder;
import com.almacen.module.folder.exception.FolderNotFoundException;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestAppConfig.class)
@WebIntegrationTest
@Transactional
public class FolderServiceTests {

    @Inject
    FolderService folderService;

    @Inject
    UserService userService;

    @Inject
    UserRoleService userRoleService;

    Folder testFolder;

    User testUser;

    @Before
    public void initObjects() {
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRoleService.saveUserRole(userRole);

        testUser = new User();
        testUser.setMail("test5@test.gmail.com");
        testUser.setUsername("testusername5");
        testUser.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));
        testUser.setPassword("testpassword5");
        userService.registerUser(testUser);

        testFolder = new Folder();
        testFolder.setFolderName("test");
        testFolder.setPhysicalPath("uploads/test");
        testFolder.setParentFolderId(0);
        testFolder.setUser(testUser);
        this.folderService.saveFolder(testFolder);
    }

    @Test
    public void saveFolder() {
        Folder newFolder = null;
        try {
            newFolder = folderService.findFolderById(testFolder.getId());
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        assertNotNull(newFolder);
        assertEquals("test", newFolder.getFolderName());
        assertEquals("uploads/test", newFolder.getPhysicalPath());
        assertEquals(0, newFolder.getParentFolderId());
        assertEquals(testUser, newFolder.getUser());
    }

    @Test
    public void updateFolder() {

        testFolder.setFolderName("newFolderName");

        folderService.updateFolder(testFolder);

        Folder newFolder = null;
        try {
            newFolder = folderService.findFolderById(testFolder.getId());
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        assertNotNull(newFolder);
        assertEquals("newFolderName", newFolder.getFolderName());
    }

    @Test
    public void updateFolderById() {

        testFolder.setFolderName("FolderTest");

        folderService.updateFolderById(testFolder.getId(),testFolder.getFolderName());

        Folder newFolder = null;
        try {
            newFolder = folderService.findFolderById(testFolder.getId());
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        assertNotNull(newFolder);
        assertEquals("FolderTest", newFolder.getFolderName());
    }

    @Test
    public void updateFolderPathById() {

        testFolder.setPhysicalPath("newTestPath");

        folderService.updateFolderPathById(testFolder.getPhysicalPath(),testFolder.getId());

        Folder newFolder = null;
        try {
            newFolder = folderService.findFolderById(testFolder.getId());
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        assertNotNull(newFolder);
        assertEquals("newTestPath", newFolder.getPhysicalPath());
    }

    @Test(expected = FolderNotFoundException.class)
    public void deleteFolderById() throws FolderNotFoundException {
        int temp = testFolder.getId();

        folderService.deleteFolderById(testFolder.getId());

        Folder deleteFolder = folderService.findFolderById(temp);
    }

    @Test
    public void checkIfParentIdExists() throws FolderNotFoundException {
        Folder testFolder2 = new Folder();
        testFolder2.setFolderName("test2");
        testFolder2.setPhysicalPath("uploads/test2");
        testFolder2.setParentFolderId(2);
        testFolder2.setUser(testUser);
        this.folderService.saveFolder(testFolder2);

        assertTrue(this.folderService.checkIfParentIdExists(testUser.getId(), testFolder2.getId()));
        assertFalse(this.folderService.checkIfParentIdExists(testUser.getId(), testFolder.getId()));

        this.folderService.deleteFolderById(testFolder2.getId());
    }

    @Test
    public void checkIfFolderWithNameExists() throws FolderNotFoundException {
        Folder testFolder2 = new Folder();
        testFolder2.setFolderName("test2");
        testFolder2.setPhysicalPath("uploads/test2");
        testFolder2.setParentFolderId(2);
        testFolder2.setUser(testUser);
        this.folderService.saveFolder(testFolder2);

        assertTrue(this.folderService.checkIfFolderWithNameExists("uploads/test2", "test2"));
        assertFalse(this.folderService.checkIfFolderWithNameExists("uploads/test2", "test"));

        this.folderService.deleteFolderById(testFolder2.getId());
    }

    @Test
    public void getPhysicalPathByFolderId() throws FolderNotFoundException {
        Folder testFolder2 = new Folder();
        testFolder2.setFolderName("test2");
        testFolder2.setPhysicalPath("uploads/test2");
        testFolder2.setParentFolderId(2);
        testFolder2.setUser(testUser);
        this.folderService.saveFolder(testFolder2);

        assertEquals("uploads/test2", this.folderService.getPhysicalPathByFolderId(testFolder2.getId()));

        this.folderService.deleteFolderById(testFolder2.getId());
    }

    @Test
    public void getFolderNameByFolderId() throws FolderNotFoundException {
        Folder testFolder2 = new Folder();
        testFolder2.setFolderName("test2");
        testFolder2.setPhysicalPath("uploads/test2");
        testFolder2.setParentFolderId(2);
        testFolder2.setUser(testUser);
        this.folderService.saveFolder(testFolder2);

        assertEquals("test2", this.folderService.getFolderNameByFolderId(testFolder2.getId()));

        this.folderService.deleteFolderById(testFolder2.getId());
    }

    @Test
    public void findFolderById() throws FolderNotFoundException {
        Folder testFolder2 = new Folder();
        testFolder2.setFolderName("test2");
        testFolder2.setPhysicalPath("uploads/test2");
        testFolder2.setParentFolderId(2);
        testFolder2.setUser(testUser);
        this.folderService.saveFolder(testFolder2);

        try {
            assertEquals("test2", folderService.findFolderById(testFolder2.getId()).getFolderName());
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        this.folderService.deleteFolderById(testFolder2.getId());
    }

    @Test
    public void findFolderByPhysicalPath() throws FolderNotFoundException {
        Folder testFolder2 = new Folder();
        testFolder2.setFolderName("test2");
        testFolder2.setPhysicalPath("uploads/test2");
        testFolder2.setParentFolderId(2);
        testFolder2.setUser(testUser);
        this.folderService.saveFolder(testFolder2);

        try {
            assertEquals("test2", folderService.
                    findFolderByPhysicalPath(testFolder2.getPhysicalPath()).getFolderName());
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        this.folderService.deleteFolderById(testFolder2.getId());
    }

    @Test
    public void findFoldersByPhysicalPath() throws FolderNotFoundException {
        Folder testFolder2 = new Folder();
        testFolder2.setFolderName("test2");
        testFolder2.setPhysicalPath("uploads/test2");
        testFolder2.setParentFolderId(2);
        testFolder2.setUser(testUser);
        this.folderService.saveFolder(testFolder2);

        List<Folder> folders = null;
        try {
            folders = folderService.findFoldersByPhysicalPath("uploads/test2");
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        assertEquals(1, folders.size());

        this.folderService.deleteFolderById(testFolder2.getId());

        try {
            folders = folderService.findFoldersByPhysicalPath("uploads/test2");
        } catch (FolderNotFoundException e) {
            fail("Folder not found.");
        }

        assertEquals(0, folders.size());

    }

}
