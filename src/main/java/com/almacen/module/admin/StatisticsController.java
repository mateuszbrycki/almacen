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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller("statisticsController")
public class StatisticsController {

    private static final Logger logger = Logger.getLogger(StatisticsController.class);
    @Inject
    private FileService fileService;
    @Inject
    private UserService userService;
    private String viewPath = "controller/admin/";

    @RequestMapping(value = AdminUrls.ADMIN_STATISTICS_FULL, method = RequestMethod.GET)
    public String statistics(ModelMap model) throws UserNotFoundException {
        List<User> userList = this.userService.findAllUser();
        model.addAttribute("userList", userList);
        return this.viewPath + "statistics";
    }

    @RequestMapping(value = AdminUrls.ADMIN_SPECIFIC_STATISTIC_ID, method = RequestMethod.GET)
    public String statistic(@PathVariable("userId") Integer userId, ModelMap model) throws UserNotFoundException {
        double wholeSizeUserFiles = wholeUserFileSizeToMB(userId);
        int maximumUploadSize = getWholeMaximumUploadSize(userId);
        HashMap<String, Integer> nameOfExtension = this.fileService.getUserFilesAllQuantity(userId);
        List<Double> percentageExtension = getPercentageOfExtension(userId, nameOfExtension);

        model.addAttribute("userId", userId);
        model.addAttribute("wholeSizeUserFiles", wholeSizeUserFiles);
        model.addAttribute("maximumUploadSize", maximumUploadSize);
        model.addAttribute("percentage", getPercentage(wholeSizeUserFiles, maximumUploadSize));
        model.addAttribute("percentageExtension", percentageExtension);
        model.addAttribute("nameExtension", nameOfExtension.keySet());

        return this.viewPath + "statistic";
    }

    private List<Double> getPercentageOfExtension(@PathVariable("userId") Integer userId, HashMap<String, Integer> quantity) {
        Integer quantityOfExtension = this.fileService.getUserFilesAllExtension(userId).size();
        List<Double> percentageExtension = new ArrayList<>();
        for(Integer extensionQuantity : quantity.values())
            percentageExtension.add(((double)extensionQuantity / (double)quantityOfExtension) * 100);
        return percentageExtension;
    }

    private int getWholeMaximumUploadSize(@PathVariable("userId") Integer userId) throws UserNotFoundException {
        User user = this.userService.findUserById(userId);
        if(user.getRole().getId() != User.REGULAR_USER)
            return User.PREMIUM_USER_PLACE; // TODO brolly Specyfication?
        else
            return User.REGULAR_USER_PLACE;
    }

    private int getPercentage(double wholeSizeUserFiles, int maximumUploadSize)
    {
        return (int)(wholeSizeUserFiles * 100) / maximumUploadSize ;
    }

    private double wholeUserFileSizeToMB(@PathVariable("userId") Integer userId) {
        Long wholeSizeUserFiles = this.fileService.getWholeSizeUserFiles(userId);
        double sizeInMB = (double)wholeSizeUserFiles / UserFile.MB;
        return round(sizeInMB, 2);
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
