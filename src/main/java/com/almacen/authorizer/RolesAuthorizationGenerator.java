package com.almacen.authorizer;

import org.apache.log4j.Logger;
import org.pac4j.core.authorization.AuthorizationGenerator;
import org.pac4j.core.profile.CommonProfile;

import java.util.Arrays;

public class RolesAuthorizationGenerator <U extends CommonProfile> implements AuthorizationGenerator<U> {

    private String rolesAttributeName = "roles";
    private String id = "id";

    private static final Logger logger = Logger.getLogger(RolesAuthorizationGenerator.class);

    @Override
    public void generate(final U profile) {
        String rolesValue = (String) profile.getAttribute(rolesAttributeName);

        profile.addRoles(Arrays.asList(rolesValue.split(",")));
        profile.setId(profile.getAttribute(id));

        logger.debug(profile);
    }
}
