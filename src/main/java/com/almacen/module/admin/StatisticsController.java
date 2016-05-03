package com.almacen.module.admin;

import com.almacen.module.file.UserFile;
import com.almacen.module.file.service.FileService;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller("statisticsController")
public class StatisticsController {

    @Inject
    private FileService fileService;

    @Inject
    private UserService userService;

    private static final Logger logger = Logger.getLogger(StatisticsController.class);

    private String viewPath = "controller/admin/";

    @RequestMapping(value = AdminUrls.ADMIN_STATISTICS_FULL, method = RequestMethod.GET)
    public String statistics(ModelMap model) throws UserNotFoundException {
        List<User> userList = this.userService.findAllUser();
        model.addAttribute("userList", userList);
        return this.viewPath + "statistics";
    }

    @RequestMapping(value = AdminUrls.ADMIN_SPECIFIC_STATISTIC_ID, method = RequestMethod.GET)
    public String statistic(@PathVariable("userId") Integer userId, ModelMap model) {
        List<UserFile> userFiles = fileService.findUserFilesByUserId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("wholeSizeUserFiles", getWholeSizeUserFiles(userFiles));
        return this.viewPath + "statistic";
    }

    private Long getWholeSizeUserFiles(List<UserFile> userFiles) {
        Long wholeSize = null;
        for (UserFile userFile : userFiles)
            wholeSize += userFile.getSize();
        return wholeSize;
    }

}
