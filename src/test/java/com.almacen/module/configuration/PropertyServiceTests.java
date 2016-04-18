package com.almacen.module.configuration;

import com.almacen.config.test.TestAppConfig;
import com.almacen.module.configuration.service.PropertyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= TestAppConfig.class)
@WebIntegrationTest
@Transactional
public class PropertyServiceTests {

    @Inject
    private PropertyService propertyService;

    private Property property;

    private static String PROPERTY_NAME = "test.property";
    private static String PROPERTY_VALUE = "test.value";


    @Before
    public void initObjects() {
        property = new Property();
        property.setPropertyName(PROPERTY_NAME);
        property.setPropertyValue(PROPERTY_VALUE);
    }

    @Test
    public void saveProperty() {
        propertyService.saveProperty(property);

        assertEquals(1, propertyService.findAll().size());
    }

    @Test
    public void getPropertyByName() {
        propertyService.saveProperty(property);
        Property foundProperty = propertyService.findOneByPropertyName(PROPERTY_NAME);

        assertEquals(foundProperty.getPropertyValue(), PROPERTY_VALUE);
    }

    @After
    public void after() {
        propertyService.deleteProperty(property);
    }

}
