package com.almacen.config;

import org.pac4j.core.config.Config;
import org.pac4j.springframework.web.RequiresAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({ "org.pac4j.springframework.web", "com.almacen"})
public class SecurityConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Config config;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequiresAuthenticationInterceptor(config, "CasClient", "user")).addPathPatterns(
                "/cas/*",
                "/app/*",
                "/api/*",
                "/user/management",
                "/user/management/*");
        registry.addInterceptor(new RequiresAuthenticationInterceptor(config, "CasClient", "admin")).addPathPatterns("/admin/*");
    }
}
