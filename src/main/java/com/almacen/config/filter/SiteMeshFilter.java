package com.almacen.config.filter;


import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/admin/*", "/views/decorators/adminDecorator.jsp")
                .addDecoratorPath("/", "/views/decorators/notAuthorizedDecorator.jsp")
                .addDecoratorPath("/user/register", "/views/decorators/notAuthorizedDecorator.jsp")
                .addDecoratorPath("/*", "/views/decorators/defaultDecorator.jsp");
    }
}