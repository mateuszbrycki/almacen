package com.almacen.config.factory;

import com.almacen.module.user.UserAbstractFactory;
import com.almacen.module.user.factory.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {

    @Bean
    public UserAbstractFactory userAbstractFactory() {
        return new UserFactory();
    }

}
