package com.lazarev.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String test() {
        return "admin_pages/users";
    }

}
