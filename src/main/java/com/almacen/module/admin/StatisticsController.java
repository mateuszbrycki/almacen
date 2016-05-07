package com.almacen.module.admin;

import com.almacen.module.file.service.FileService;
import com.almacen.module.statistics.service.StatisticsService;
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
import java.util.HashMap;
import java.util.List;

@Controller("statisticsController")
public class StatisticsController {

    private static final Logger logger = Logger.getLogger(StatisticsController.class);
    @Inject
    private FileService fileService;
    @Inject
    private UserService userService;

    @Inject
    private StatisticsService statisticsService;

    private String viewPath = "controller/admin/";

    @RequestMapping(value = AdminUrls.ADMIN_STATISTICS_FULL, method = RequestMethod.GET)
    public String statistics(ModelMap model) throws UserNotFoundException {
        List<User> userList = this.userService.findAllUser();
        model.addAttribute("userList", userList);
        return this.viewPath + "statistics";
    }

    @RequestMapping(value = AdminUrls.ADMIN_SPECIFIC_STATISTIC_ID, method = RequestMethod.GET)
    public String statistic(@PathVariable("userId") Integer userId, ModelMap model) throws UserNotFoundException {
        double wholeSizeUserFiles = this.statisticsService.wholeUserFileSizeToMB(userId);
        int maximumUploadSize = this.statisticsService.getWholeMaximumUploadSize(userId);
        HashMap<String, Integer> nameOfExtension = this.fileService.getUserFilesAllQuantity(userId);
        List<Double> percentageExtension = this.statisticsService.getPercentageOfExtension(userId, nameOfExtension);

        model.addAttribute("userId", userId);
        model.addAttribute("wholeSizeUserFiles", wholeSizeUserFiles);
        model.addAttribute("maximumUploadSize", maximumUploadSize);
        model.addAttribute("percentage", this.statisticsService.getPercentage(wholeSizeUserFiles, maximumUploadSize));
        model.addAttribute("percentageExtension", percentageExtension);
        model.addAttribute("nameExtension", nameOfExtension.keySet());

        return this.viewPath + "statistic";
    }



}
