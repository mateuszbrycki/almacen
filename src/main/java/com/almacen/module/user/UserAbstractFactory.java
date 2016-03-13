package com.almacen.module.user;

import com.almacen.AbstractFactory;
import com.almacen.module.user.dto.UserDTO;

public interface UserAbstractFactory extends AbstractFactory {
    User createFromDTO(UserDTO userDTO);
    User createFromObject(Object[] userObject);
}
