package com.almacen.module.user.factory;

import com.almacen.module.user.User;
import com.almacen.module.user.UserAbstractFactory;
import com.almacen.module.user.dto.UserDTO;
import com.almacen.module.userrole.UserRole;
import com.almacen.module.userrole.service.UserRoleService;

import javax.inject.Inject;
//TODO mbrycki broken SRP
public class UserFactory implements UserAbstractFactory {

    @Inject
    private UserRoleService userRoleService;

    @Override
    public User createFromDTO(UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setMail(userDTO.getMail());
        user.setPassword(userDTO.getPassword());
        user.setIsActive(User.DEFAULT_IS_ACTIVE);
        user.setRole(userRoleService.findByRole(User.DEFAULT_ROLE));

        return user;
    }

    @Override
    public User createFromObject(Object[] userObject) {

        User user = new User();
        user.setId((Integer) userObject[0]);
        user.setUsername((String) userObject[1]);
        user.setMail((String) userObject[3]);
        user.setPassword((String) userObject[4]);
        user.setIsActive((Boolean)userObject[5]);

        if(userObject.length > 8) {
            UserRole userRole = new UserRole();
            userRole.setId((Integer) userObject[2]);
            userRole.setRole((String) userObject[8]);

            user.setRole(userRole);
        } else {
           user.setRole(userRoleService.findById((Integer) userObject[2]));
        }

        return user;
    }
}
