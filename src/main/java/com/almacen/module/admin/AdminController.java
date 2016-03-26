package com.almacen.module.admin;

import com.almacen.logger.LoggerAbstract;

import com.almacen.logger.status.Status;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(AdminUrls.ADMIN)
public class AdminController  {

    @Inject
    private LoggerAbstract systemLogger;

    private static final Logger logger = Logger.getLogger(AdminController.class);

    private String viewPath = "controller/admin/";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminMain(HttpServletRequest request, HttpServletResponse response) {

        this.systemLogger.log(Status.INFO, "New Message");
        this.systemLogger.log(Status.ERROR, "New Message", request);

        return this.viewPath + "index";
    }

}
