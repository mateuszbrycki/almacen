package com.almacen.module.user;

import com.almacen.module.admin.AdminUrls;
import com.almacen.module.base.BaseUrls;
import com.almacen.module.user.dto.UserDTO;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.module.user.service.UserServiceImpl;
import com.almacen.module.userrole.UserRole;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(UserUrls.USER)
public class UserController {

    @Inject
    private UserAbstractFactory userFactory;

    @Inject
    private UserService userService;

    @Inject
    private MessageSource messageSource;

    @Inject
    private Environment environment;

    private static final Logger logger = Logger.getLogger(UserController.class);

    private String viewPath = "controller/user/";

    private String[] args = {};

    @RequestMapping(value = UserUrls.USER_LOGOUT, method = RequestMethod.GET)
    public String logoutPage() {

        return "redirect:" + environment.getProperty("pac4j.application.logout");
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.GET)
    public String registerFormPage(HttpServletRequest request, HttpServletResponse response) {

        if (UserUtils.isAutheniticated(request, response)) {
            return "redirect:" + BaseUrls.APPLICATION;
        }

        return this.viewPath + "register";
    }

    @RequestMapping(value = UserUrls.USER_REGISTER, method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
    public String registerPage(@ModelAttribute @Valid UserDTO userDTO,
                               ModelMap model, Locale locale) {
        logger.debug(userDTO);

        Boolean passwordsAreEqual = arePasswordsEqual(userDTO.getPassword(), userDTO.getPasswordRepeat());

        if (passwordsAreEqual) {
            User user = userFactory.createFromDTO(userDTO);

            //TODO mbrycki command object
            if (this.userService.registerUser(user)) {

                model.addAttribute("success", messageSource.getMessage("user.message.success.register", args, locale));
            } else {
                model.addAttribute("error", messageSource.getMessage("user.message.error", args, locale));
            }
        } else {
            model.addAttribute("error", messageSource.getMessage("user.message.error.passwords", args, locale));

        }

        return this.viewPath + "register";
    }

    @RequestMapping(value = UserUrls.USER_MANAGEMENT, method = RequestMethod.GET)
    public String managementPage(HttpServletRequest request, HttpServletResponse response) {

        return this.viewPath + "management";
    }

    @RequestMapping(value = UserUrls.USER_PASSWORD_CHANGE, method = RequestMethod.POST)
    public String changePasswordPage(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam("password_old") String oldPassword,
                                     @RequestParam("password") String newPassword,
                                     @RequestParam("password_repeat") String passwordRepeat,
                                     RedirectAttributes attributes, Locale locale) throws UserNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);

        User user = this.userService.findUserById(userId);

        Boolean arePasswordsEqual = this.arePasswordsEqual(newPassword, passwordRepeat);
        Boolean isOldPasswordCorrect = this.isOldPasswordCorrect(oldPassword, user);

        if (arePasswordsEqual && isOldPasswordCorrect) {
            this.updateUserPassword(user, newPassword);
            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.passwords", args, locale));
        } else {
            attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.passwords", args, locale));
        }

        return "redirect:" + UserUrls.USER_MANAGEMENT_FULL;
    }

    private Boolean arePasswordsEqual(String password, String passwordRepeat) {
        return password.equals(passwordRepeat);
    }

    private Boolean isOldPasswordCorrect(String oldPassword, User user) {
        return user.getPassword().equals(oldPassword);
    }

    private void updateUserPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        this.userService.updateUser(user);
    }

    @RequestMapping(value = UserUrls.USER_USERNAME_CHANGE, method = RequestMethod.POST)
    public String changeUsernamePage(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam("username") String username,
                                     RedirectAttributes attributes, Locale locale) throws UserNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);

        //check if username doesn't exist in database
        Boolean usernameExists = this.doesUserExist(username);

        if (!usernameExists) {
            this.updateUserUsername(userId, username);
            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.username", args, locale));
        } else {
            attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.username", args, locale));
        }

        return "redirect:" + UserUrls.USER_MANAGEMENT_FULL;
    }

    private Boolean doesUserExist(String username) {
        return this.userService.checkIfUserWithUsernameExists(username);
    }

    private void updateUserUsername(Integer userId, String newUsername) throws UserNotFoundException {
        User user = this.userService.findUserById(userId);
        user.setUsername(newUsername);
        this.userService.updateUser(user);
    }

    @RequestMapping(value = UserUrls.USER_DELETE, method = RequestMethod.POST)
    public String deleteAccountAction(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam("password") String password,
                                      RedirectAttributes attributes, Locale locale) throws UserNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);
        User user = this.userService.findUserById(userId);

        Boolean isOldPasswordCorrect = this.isOldPasswordCorrect(password, user);
        if (isOldPasswordCorrect) {
            this.userService.deleteUserById(userId);
            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.delete", args, locale));
            return "redirect:" + UserUrls.USER_LOGOUT_FULL;
        }

        attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.delete", args, locale));
        return "redirect:" + UserUrls.USER_MANAGEMENT_FULL;
    }

    @RequestMapping(value = UserUrls.USER_EDIT_MANAGEMENT_ID, method = RequestMethod.GET)
    public String editManagementPage(@PathVariable("userId") Integer userId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        model.addAttribute("userId", userId);
        return this.viewPath + "editmanagement";
    }

    @RequestMapping(value = UserUrls.USER_LIST, method = RequestMethod.GET)
    public String userList(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UserNotFoundException {
        List<User> userList = this.userService.findAllUser();

        model.addAttribute("userList", userList);

        return this.viewPath + "userList";
    }

    private void changeRole(Integer userId, int newRoleId) throws UserNotFoundException {
        User user = this.userService.findUserById(userId);
        UserRole newRole = new UserRole();
        newRole.setId(newRoleId);
        user.setRole(newRole);
        this.userService.updateUser(user);
    }

    @RequestMapping(value = UserUrls.ADMIN_CHANGE_ROLE, method = RequestMethod.POST)
    public String changeRolePage(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam("userId") int userId,
                                 @RequestParam("roleId") int roleId,
                                 RedirectAttributes attributes, Locale locale) throws UserNotFoundException {
        this.changeRole(userId, roleId);
        attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.changeRole", args, locale));

        return "redirect:" + UserUrls.USER_LIST_FULL;
    }


    @RequestMapping(value = UserUrls.ADMIN_PASSWORD_CHANGE, method = RequestMethod.POST)
    public String changePasswordAdminPage(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam("userId") int userId,
                                          @RequestParam("password") String newPassword,
                                          @RequestParam("password_repeat") String passwordRepeat,
                                          RedirectAttributes attributes, Locale locale) throws UserNotFoundException {

        User user = this.userService.findUserById(userId);

        Boolean arePasswordsEqual = this.arePasswordsEqual(newPassword, passwordRepeat);

        if (arePasswordsEqual) {
            this.updateUserPassword(user, newPassword);
            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.passwords", args, locale));
        } else {
            attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.passwords", args, locale));
        }

        return "redirect:" + UserUrls.USER_LIST_FULL;
    }

    @RequestMapping(value = UserUrls.ADMIN_USERNAME_CHANGE, method = RequestMethod.POST)
    public String changeUsernameAdminPage(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam("userId") int userId,
                                          @RequestParam("username") String username,
                                          RedirectAttributes attributes, Locale locale) throws UserNotFoundException {

        Boolean usernameExists = this.doesUserExist(username);

        if (!usernameExists) {
            this.updateUserUsername(userId, username);
            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.username", args, locale));
        } else {
            attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.username", args, locale));
        }

        return "redirect:" + UserUrls.USER_LIST_FULL;
    }

    @RequestMapping(value = UserUrls.ADMIN_DELETE, method = RequestMethod.POST)
    public String deleteAccountAdminAction(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam("userId") int userId,
                                           @RequestParam("password") String password,
                                           RedirectAttributes attributes, Locale locale) throws UserNotFoundException {

        User user = this.userService.findUserById(userId);

        Boolean isOldPasswordCorrect = this.isOldPasswordCorrect(password, user);
        if (isOldPasswordCorrect) {
            this.userService.deleteUserById(user.getId());
            attributes.addFlashAttribute("success", messageSource.getMessage("user.message.success.delete", args, locale));
            return "redirect:" + UserUrls.USER_LOGOUT_FULL;
        }

        attributes.addFlashAttribute("error", messageSource.getMessage("user.message.error.delete", args, locale));
        return "redirect:" + UserUrls.USER_LIST_FULL;
    }
}