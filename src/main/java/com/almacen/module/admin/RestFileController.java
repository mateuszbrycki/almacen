package com.almacen.module.admin;

import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.PropertyAbstractFactory;
import com.almacen.module.configuration.dto.PropertyDTO;
import com.almacen.module.configuration.service.PropertyService;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping(AdminUrls.Api.ADMIN_FILE)
public class RestFileController {

    @Inject
    private PropertyAbstractFactory propertyFactory;

    @Inject
    private PropertyService propertyService;

    @Inject
    private MessageSource messageSource;

    private static final Logger logger = Logger.getLogger(RestFileController.class);

    private String[] args = {};


    @RequestMapping(value = AdminUrls.Api.ADMIN_FILE_EXTENSION, method = RequestMethod.POST)
    public ResponseEntity<Object> saveProperty(HttpServletRequest request,
                                             HttpServletResponse response,
                                              @RequestBody @Valid PropertyDTO blockedExtensions) {

        Property property = this.propertyFactory.createFromDTO(blockedExtensions);
        propertyService.saveProperty(property);

        return new ResponseEntity<Object>(property, HttpStatus.OK);

    }

    @RequestMapping(value = AdminUrls.Api.ADMIN_MAXIMUM_SIZE_FILE, method = RequestMethod.POST)
    public String changeMaximumSize(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody @Valid PropertyDTO maximumSize, BindingResult result,
                                    Model model, Locale locale) {

        Property property = this.propertyFactory.createFromDTO(maximumSize);
        propertyService.saveProperty(property);

        if (request.getMethod().equalsIgnoreCase("get") || result.hasErrors())
        {
            model.addAttribute("success", messageSource.getMessage("success", args, locale));
        } else {
            model.addAttribute("error", messageSource.getMessage("error", args, locale));
        }
        return "redirect:" + AdminUrls.Api.ADMIN_FILE;
    }
}
