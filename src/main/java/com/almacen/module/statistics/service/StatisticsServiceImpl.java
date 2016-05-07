package com.almacen.module.statistics.service;


import com.almacen.module.file.UserFile;
import com.almacen.module.file.service.FileService;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl implements StatisticsService{

    @Resource
    FileService fileService;

    @Resource
    UserService userService;

    @Override
    public List<Double> getPercentageOfExtension(Integer userId, HashMap<String, Integer> quantity) {
        Integer quantityOfExtension = this.fileService.getUserFilesAllExtension(userId).size();
        List<Double> percentageExtension = new ArrayList<>();
        for(Integer extensionQuantity : quantity.values())
            percentageExtension.add(((double)extensionQuantity / (double)quantityOfExtension) * 100);
        return percentageExtension;
    }

    @Override
    public int getWholeMaximumUploadSize(Integer userId) throws UserNotFoundException {
        User user = this.userService.findUserById(userId);
        if(user.getRole().getId() != User.REGULAR_USER)
            return User.PREMIUM_USER_PLACE; // TODO brolly Specyfication?
        else
            return User.REGULAR_USER_PLACE;
    }

    @Override
    public int getPercentage(double wholeSizeUserFiles, int maximumUploadSize)
    {
        return (int)(wholeSizeUserFiles * 100) / maximumUploadSize ;
    }

    @Override
    public double wholeUserFileSizeToMB(Integer userId) {
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
