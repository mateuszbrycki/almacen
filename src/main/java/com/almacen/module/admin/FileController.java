package com.almacen.module.admin;

import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.service.PropertyService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("fileSettingsController")
@RequestMapping(AdminUrls.ADMIN_FILE)
public class FileController {

    @Inject
    private PropertyService propertyService;

    private static final Logger logger = Logger.getLogger(FileController.class);

    private String viewPath = "controller/admin/file/";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String adminMain(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

       // userActionLogger.log(Status.INFO, LoggerMessages.ADMIN_ACCESS_FILES, request, response);

        Property property = this.propertyService.findBlockedFileExtensions();
        Property propertyMaximumUploadSizeFile = this.propertyService.maximumUploadSizeFile();
        model.addAttribute("blockedExtensions", property);
        model.addAttribute("maximumUploadSizeFile", propertyMaximumUploadSizeFile);

        return this.viewPath + "index";
    }
}
