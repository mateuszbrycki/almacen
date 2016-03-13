package com.almacen.config;

import com.almacen.authorizer.CustomAuthorizer;
import com.almacen.authorizer.RolesAuthorizationGenerator;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.logout.CasSingleSignOutHandler;
import org.pac4j.core.authorization.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class Pac4jConfig {

    @Value("${salt}")
    private String salt;

    @Inject
    Environment environment;

    @Bean
    public Config config() {

        final CasClient casClient = new CasClient();
        // casClient.setGateway(true);
        casClient.setCasLoginUrl(environment.getProperty("cas.login.page"));
        casClient.setCasProtocol(CasClient.CasProtocol.CAS20);
        casClient.setLogoutHandler(new CasSingleSignOutHandler());
        casClient.addAuthorizationGenerator(new RolesAuthorizationGenerator());

        final Clients clients = new Clients(environment.getProperty("pac4j.application.callback"), casClient);

        final Config config = new Config(clients);
        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"));
        config.addAuthorizer("user", new RequireAnyRoleAuthorizer("ROLE_USER", "ROLE_ADMIN"));
        config.addAuthorizer("custom", new CustomAuthorizer());

        return config;
    }
}