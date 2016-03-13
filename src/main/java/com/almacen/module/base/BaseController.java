package com.almacen.module.base;

import com.almacen.util.UserUtils;
import org.pac4j.core.exception.RequiresHttpAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class BaseController {

    private String viewPath = "controller/default/";

    @RequestMapping("/")
    public String root(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws RequiresHttpAction {

        if(UserUtils.isAutheniticated(request, response)) {
            return "redirect:" + BaseUrls.APPLICATION;
        }

        return this.viewPath + "main";
    }
}
