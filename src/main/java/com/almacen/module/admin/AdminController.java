package com.almacen.module.admin;

import com.almacen.logger.LoggerAbstract;

import com.almacen.logger.LoggerMessage;
import com.almacen.logger.service.LoggerService;
import com.almacen.logger.status.Status;
import com.almacen.module.user.User;
import com.almacen.module.user.UserUrls;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.LoggerMessages;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(AdminUrls.ADMIN)
public class AdminController  {

    @Inject
    private LoggerAbstract userActionLogger;

    @Inject
    private LoggerService loggerService;

    @Inject
    private UserService userService;

    private static final Logger logger = Logger.getLogger(AdminController.class);

    private String viewPath = "controller/admin/";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminMain(HttpServletRequest request, HttpServletResponse response) {

        userActionLogger.log(Status.INFO, LoggerMessages.ADMIN_ACCESS, request, response);

        //TODO mbrycki administrator dashboard

        return this.viewPath + "index";
    }

    @RequestMapping(value = AdminUrls.ADMIN_LOGS, method = RequestMethod.GET)
    public String logs(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List<LoggerMessage> loggerMessages = this.loggerService.findAll();

        model.addAttribute("messages", loggerMessages);
        logger.debug(loggerMessages);

        return this.viewPath + "logs";
    }

    @RequestMapping(value = AdminUrls.ADMIN_LOGS_USER, method = RequestMethod.GET)
    public String userLogs(
            @PathVariable("userId") Integer userId,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model) {

        List<LoggerMessage> loggerMessages = this.loggerService.findAllByUserId(userId);

        model.addAttribute("messages", loggerMessages);
        logger.debug(loggerMessages);

        return this.viewPath + "logs";
    }
}