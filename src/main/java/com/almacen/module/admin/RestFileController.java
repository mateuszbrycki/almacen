package com.almacen.module.admin;

import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.PropertyAbstractFactory;
import com.almacen.module.configuration.dto.PropertyDTO;
import com.almacen.module.configuration.service.PropertyService;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(AdminUrls.Api.ADMIN_FILE)
public class RestFileController {

    @Inject
    private PropertyAbstractFactory propertyFactory;

    @Inject
    private PropertyService propertyService;

    @Inject
    private Environment environment;

    private static final Logger logger = Logger.getLogger(RestFileController.class);

    private String[] args = {};


    @RequestMapping(value = AdminUrls.Api.ADMIN_FILE_EXTENSION, method = RequestMethod.POST)
    public ResponseEntity<Object> saveProperty(HttpServletRequest request,
                                             HttpServletResponse response,
                                              @RequestBody @Valid PropertyDTO blockedExtensions) {

        blockedExtensions.setPropertyName(environment.getProperty("property.extensions.blocked.name"));
        Property property = this.propertyFactory.createFromDTO(blockedExtensions);
        propertyService.saveProperty(property);

        return new ResponseEntity<Object>(property, HttpStatus.OK);

    }

    @RequestMapping(value = AdminUrls.Api.ADMIN_MAXIMUM_SIZE_FILE, method = RequestMethod.POST)
    public ResponseEntity<Object> changeMaximumSize(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestBody @Valid PropertyDTO maximumUploadSizeFile) {

        maximumUploadSizeFile.setPropertyName(environment.getProperty("property.file.maximum_size"));
        Property property = this.propertyFactory.createFromDTO(maximumUploadSizeFile);
        propertyService.saveProperty(property);
        return new ResponseEntity<Object>(property, HttpStatus.OK);
    }
}
