package com.almacen.module.admin;

import com.almacen.logger.LoggerAbstract;
import com.almacen.logger.LoggerMessage;
import com.almacen.logger.service.LoggerService;
import com.almacen.module.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller("logsController")
@RequestMapping(AdminUrls.ADMIN_LOGS)
public class LogsController {

    @Inject
    private LoggerService loggerService;


    private static final Logger logger = Logger.getLogger(AdminController.class);

    private String viewPath = "controller/admin/logs/";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String logs(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        List<LoggerMessage> loggerMessages = this.loggerService.findAll();

        model.addAttribute("messages", loggerMessages);
        logger.debug(loggerMessages);

        return this.viewPath + "index";
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

        return this.viewPath + "index";
    }
}
