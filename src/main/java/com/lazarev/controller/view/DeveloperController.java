package com.lazarev.controller.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DeveloperController {

    @RequestMapping(value = "/become_developer",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('USER')")
    public String becomeDeveloper() {
        return "common_pages/become_developer";
    }

    @RequestMapping(value = "/developer_management",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getManagemantPage() {
        return "common_pages/developer_management";
    }
}
