package com.almacen.module.statistics.service;


import com.almacen.module.user.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface StatisticsService {

    List<Double> getPercentageOfExtension(Integer userId, HashMap<String, Integer> quantity);

    int getWholeMaximumUploadSize(Integer userId) throws UserNotFoundException;

    int getPercentage(double wholeSizeUserFiles, int maximumUploadSize);

    double wholeUserFileSizeToMB(Integer userId);
}
