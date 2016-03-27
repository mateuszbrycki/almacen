package com.almacen.config.test;

import com.almacen.config.HibernateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class TestHibernateConfig extends HibernateConfig {

    @Override
    @Bean(name="dataSourceMySQL")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.h2.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.h2.url"));
        return dataSource;
    }

    @Override
    protected Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.h2.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }
}
