package com.almacen.config.test;

import com.almacen.config.AppConfig;
import com.almacen.config.Pac4jConfig;
import com.almacen.config.SecurityConfig;
import com.almacen.config.factory.FactoryConfig;
import com.almacen.config.logger.LoggerConfig;
import org.springframework.context.annotation.Import;

@Import({ SecurityConfig.class,
        TestHibernateConfig.class,
        Pac4jConfig.class,
        FactoryConfig.class,
        LoggerConfig.class
})
public class TestAppConfig extends AppConfig {
}
