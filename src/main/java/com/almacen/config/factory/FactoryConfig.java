package com.almacen.config.factory;

import com.almacen.module.configuration.PropertyAbstractFactory;
import com.almacen.module.configuration.factory.PropertyFactory;
import com.almacen.module.folder.FolderAbstractFactory;
import com.almacen.module.folder.factory.FolderFactory;
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

    @Bean
    public PropertyAbstractFactory propertyAbstractFactory() {
        return new PropertyFactory();
    }

    @Bean
    public FolderAbstractFactory folderAbstractFactory() { return new FolderFactory(); }

}
