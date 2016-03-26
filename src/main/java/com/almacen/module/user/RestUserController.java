package com.almacen.module.user;

import com.almacen.Select2Response;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UserUrls.Api.USER)
public class RestUserController {

    @Inject
    UserService userService;

    @RequestMapping(value = {UserUrls.Api.USERNAME, "/"}, method = RequestMethod.GET)
    public ResponseEntity<Object> getMessage(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestParam Map<String,String> allRequestParams)  {

        String usernameParameter = allRequestParams.get("username");
        Integer userId = UserUtils.getUserId(request, response);

        List<Select2Response> responseObject = new ArrayList<>();

        List<User> users;

        try {
            users = userService.findUsersByUsername(usernameParameter, userId);
        } catch(UserNotFoundException e) {
            users = new ArrayList<>();
        }

        for (User user : users) {
            responseObject.add(new Select2Response(String.valueOf(user.getId()), user.getUsername()));
        }

        return new ResponseEntity<Object>(responseObject, HttpStatus.OK);
    }

}
