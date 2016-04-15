package com.almacen.module.admin;

import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.PropertyAbstractFactory;
import com.almacen.module.configuration.dto.PropertyDTO;
import com.almacen.module.configuration.service.PropertyService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private static final Logger logger = Logger.getLogger(RestFileController.class);


    @RequestMapping(value = AdminUrls.Api.ADMIN_FILE_EXTENSION, method = RequestMethod.POST)
    public ResponseEntity<Object> saveProperty(HttpServletRequest request,
                                             HttpServletResponse response,
                                              @RequestBody @Valid PropertyDTO blockedExtensions) {

        Property property = this.propertyFactory.createFromDTO(blockedExtensions);
        propertyService.saveProperty(property);

        return new ResponseEntity<Object>(property, HttpStatus.OK);

    }
}
